package com.corphelper.mailparser.service.impl;

import com.corphelper.mailparser.constant.ParserConstant;
import com.corphelper.mailparser.constant.PartStorageConstant;
import com.corphelper.mailparser.dto.MailInfoDto;
import com.corphelper.mailparser.entity.FileInfo;
import com.corphelper.mailparser.entity.MailInfo;
import com.corphelper.mailparser.entity.Part;
import com.corphelper.mailparser.entity.PartStorage;
import com.corphelper.mailparser.exeption_handler.exception.EmptyFileNotFoundException;
import com.corphelper.mailparser.exeption_handler.exception.WorkBookCreationIOException;
import com.corphelper.mailparser.exeption_handler.exception.WrongPartStorageKeyException;
import com.corphelper.mailparser.mapper.MailInfoMapper;
import com.corphelper.mailparser.repository.FileInfoRepository;
import com.corphelper.mailparser.repository.PartRepository;
import com.corphelper.mailparser.repository.TransactionPartRepository;
import com.corphelper.mailparser.service.MailInfoService;
import com.corphelper.mailparser.service.MailParserService;
import com.corphelper.mailparser.util.FileUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
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


@Data
@RequiredArgsConstructor
@Service
public class MailParserServiceImpl implements MailParserService {

    private final MailInfoMapper mailInfoMapper;

    private final PartRepository partRepository;

    private final TransactionPartRepository transactionPartRepository;

    private final FileInfoRepository fileInfoRepository;

    private final MailInfoService mailInfoService;

    @Value("${file.path}")
    private String filePath;


    @Transactional
    @Override
    public void pars(List<MailInfoDto> mailInfoDtoList) {

        List<MailInfo> mailInfoList = mailInfoMapper.mapToMailInfoList(mailInfoDtoList);
        mailInfoService.setLocalDateTimeAndId(mailInfoList);
        mailInfoService.saveAll(mailInfoList);
        parsMailInfos(mailInfoList);

    }

    private void parsMailInfos(List<MailInfo> mailInfoList) {

        for (MailInfo mailInfo : mailInfoList) {

            parsFileInfos(mailInfo);

        }
    }

    private void parsFileInfos(MailInfo mailInfo) {

        for (FileInfo fileInfo : mailInfo.getFileInfoList()) {

            List<Part> parts = new ArrayList<>();
            String fileName = getFileName(fileInfo);

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

    private void deletePreviousPartsAndSaveCurrent(List<Part> parts, short partStorageId) {

        partRepository.delete(partStorageId);
        partRepository.saveAll(parts);
        transactionPartRepository.saveAll(parts);

    }

    private void tryParsAllFileRows(File file, List<Part> parts) {

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


    private void iterateAllRows(List<Part> parts, Workbook workbook) {

        Sheet firstSheet = workbook.getSheetAt(ParserConstant.FIRST_SHEET);

        for (int currentRow = 0; currentRow < firstSheet.getLastRowNum(); currentRow++) {

            if (currentRow == ParserConstant.FIRST_ROW) {
                continue;
            }

            Row nextRow = firstSheet.getRow(currentRow);
            Part part = iterateOneRowAndGetPart(nextRow);
            parts.add(part);

        }
    }

    private short setPartStorageIdAndId(String storageKey, List<Part> parts) {

        PartStorage partStorage = getPartStorage(storageKey);

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

    private Part iterateOneRowAndGetPart(Row nextRow) {

        Part part = new Part();

        for (short currentColumn = 0; currentColumn <= 4; currentColumn++) {

            Cell cell = nextRow.getCell(currentColumn);
            getAndSetCellTypeToPart(cell, part, currentColumn);
            part.setCreateDate(LocalDateTime.now());

        }

        return part;
    }

    private void getAndSetCellTypeToPart(Cell cell, Part part, short column) {

        switch (column) {

            case 0:
                part.setCode(cell.getStringCellValue());
                break;

            case 1:
                part.setBrand(cell.getStringCellValue());
                break;

            case 2:
                part.setDescription(cell.getStringCellValue());
                break;

            case 3:
                break;

            case 4:
                part.setCount((int) cell.getNumericCellValue());
                break;

        }
    }
}


