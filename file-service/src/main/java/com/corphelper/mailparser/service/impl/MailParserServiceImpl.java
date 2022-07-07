package com.corphelper.mailparser.service.impl;

import com.corphelper.mailparser.constant.ParserConstant;
import com.corphelper.mailparser.constant.PartStorageConstant;
import com.corphelper.mailparser.dto.MailInfoDto;
import com.corphelper.mailparser.entity.FileInfo;
import com.corphelper.mailparser.entity.MailInfo;
import com.corphelper.mailparser.entity.part.Brand;
import com.corphelper.mailparser.entity.part.Part;
import com.corphelper.mailparser.entity.part.PartInfo;
import com.corphelper.mailparser.entity.part.PartStorage;
import com.corphelper.mailparser.exeption_handler.exception.EmptyFileNotFoundException;
import com.corphelper.mailparser.exeption_handler.exception.WorkBookCreationIOException;
import com.corphelper.mailparser.exeption_handler.exception.WrongPartStorageKeyException;
import com.corphelper.mailparser.mapper.MailInfoMapper;
import com.corphelper.mailparser.repository.*;
import com.corphelper.mailparser.service.MailInfoService;
import com.corphelper.mailparser.service.MailParserService;
import com.corphelper.mailparser.util.FileUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Data
@Slf4j
@RequiredArgsConstructor
public class MailParserServiceImpl implements MailParserService {

    private final MailInfoMapper mailInfoMapper;

    private final PartInfoRepository partInfoRepository;

    private final TransactionPartRepository transactionPartRepository;

    private final FileInfoRepository fileInfoRepository;

    private final MailInfoService mailInfoService;

    private final PartRepository partRepository;

    private final BrandRepository brandRepository;

    @Value("${file.path}")
    private String filePath;


    @Transactional
    @Override
    public void pars(List<MailInfoDto> mailInfoDtoList) {

        log.info("Map mailInfoDtoList to mailInfoList");

        List<MailInfo> mailInfoList = mailInfoMapper.mapToMailInfoList(mailInfoDtoList);
        mailInfoService.setLocalDateTimeAndId(mailInfoList);

        log.info("Try to save mailInfoList");
        mailInfoService.saveAll(mailInfoList);

        parsMailInfos(mailInfoList);

    }

    private void parsMailInfos(List<MailInfo> mailInfoList) {

        for (MailInfo mailInfo : mailInfoList) {

            log.info("Try to parse fileInfoList");
            parsFileInfos(mailInfo);

        }
    }

    private void parsFileInfos(MailInfo mailInfo) {

        for (FileInfo fileInfo : mailInfo.getFileInfoList()) {

            List<PartInfo> parts = new ArrayList<>();
            String fileName = getFileName(fileInfo);

            log.info("Try to get File from byte array");
            File file = FileUtil.getFile(filePath, fileName, fileInfo.getFileBytes());
            tryParsAllFileRows(file, parts);

            String storageKey = fileInfo.getFileName();
            short partStorageId = setPartStorageIdAndId(storageKey, parts);

            deletePreviousPartsAndSaveCurrent(parts, partStorageId);
            setIdMailInfoIdAndSave(mailInfo.getId(), fileInfo);

        }
    }

    private void setIdMailInfoIdAndSave(UUID mailInfoId, FileInfo fileInfo) {

        fileInfo.setMailInfoId(mailInfoId);
        fileInfo.setId(UUID.randomUUID());
        fileInfoRepository.save(fileInfo);

    }

    private String getFileName(FileInfo fileInfo) {

        return fileInfo.getFileName() + "." + fileInfo.getExtension();
    }

    private void deletePreviousPartsAndSaveCurrent(List<PartInfo> partInfoList, short partStorageId) {

        partInfoRepository.delete(partStorageId);
        partInfoRepository.saveAll(partInfoList);
        transactionPartRepository.saveAll(partInfoList);

    }

    private void tryParsAllFileRows(File file, List<PartInfo> parts) {

        log.info("Parse file rows.");

        try (FileInputStream fileStream = new FileInputStream(file);
             Workbook workbook = WorkbookFactory.create(fileStream)) {

            iterateAllRows(parts, workbook);

        } catch (FileNotFoundException e) {

            throw new EmptyFileNotFoundException("Wrong file path.");

        } catch (IOException exception) {

            throw new WorkBookCreationIOException("Exception when Work Book creating.");

        } finally {

            FileUtil.tryDeleteFile(file);

        }
    }

    private void iterateAllRows(List<PartInfo> parts, Workbook workbook) {

        Sheet firstSheet = workbook.getSheetAt(ParserConstant.FIRST_SHEET);

        for (int currentRow = 0; currentRow < firstSheet.getLastRowNum(); currentRow++) {

            if (checkFirstRow(currentRow)) continue;

            Row nextRow = firstSheet.getRow(currentRow);
            PartInfo partInfo = iterateOneRowAndGetPart(nextRow);

            if(partInfo != null) parts.add(partInfo);

        }
    }

    private boolean checkFirstRow(int currentRow) {

        if (currentRow == ParserConstant.FIRST_ROW) {

            return true;

        }
        return false;
    }

    private short setPartStorageIdAndId(String storageKey, List<PartInfo> parts) {

        PartStorage partStorage = getPartStorage(storageKey);

        log.info("Set part storage id to parts");

        parts.forEach(x -> {
            x.setPartStorageId(partStorage.getId());
            x.setId(UUID.randomUUID());
        });

        return partStorage.getId();
    }

    private PartStorage getPartStorage(String storageKey) {

        return Optional.of(PartStorageConstant.PART_STORAGE_MAP.get(storageKey))
                .orElseThrow(() -> new WrongPartStorageKeyException("Wrong part storage key " + storageKey));
    }

    private PartInfo iterateOneRowAndGetPart(Row nextRow) {

        PartInfo partInfo = new PartInfo();
        Part part = new Part();
        Brand brand = new Brand();

        for (short currentColumn = 0; currentColumn <= 5; currentColumn++) {

            Cell cell = nextRow.getCell(currentColumn);
            getAndSetCellTypeToPart(cell, partInfo, part, brand, currentColumn);
            partInfo.setCreateDate(LocalDateTime.now());

        }

        return checkBrandAndCompletePartInfo(partInfo, part, brand);
    }

    private PartInfo checkBrandAndCompletePartInfo(PartInfo partInfo, Part part, Brand brand) {

        Optional<Brand> optionalBrand = brandRepository.getByName(brand.getName());

        if (optionalBrand.isPresent()) {

            checkPartIdAndSetInfo(part, optionalBrand);
            partInfo.setPart(part);

            return partInfo;

        } else {

            log.error("Brand " + brand.getName() + " is no present.");
            return null;

        }
    }

    private void checkPartIdAndSetInfo(Part part, Optional<Brand> optionalBrand) {

        Brand brand;
        brand = optionalBrand.get();
        Optional<UUID> id = partRepository.getIdByCodeAndBrand(part.getCode(), brand.getName());

        if (id.isPresent()) {

            part.setId(id.get());

        } else {

            part.setCreateDate(LocalDateTime.now());
            part.setId(UUID.randomUUID());
            part.setBrand(brand);

            partRepository.save(part);

        }
    }

    private void getAndSetCellTypeToPart(Cell cell, PartInfo partInfo, Part part, Brand brand, short column) {

        switch (column) {

            case 0:
                part.setCode(cell.getStringCellValue());
                break;

            case 1:
                brand.setName(cell.getStringCellValue());
                break;

            case 2:
                part.setDescription(cell.getStringCellValue());
                break;

            case 3:

            case 4:
                break;

            case 5:
                partInfo.setCount((int) cell.getNumericCellValue());
                break;

        }
    }
}


