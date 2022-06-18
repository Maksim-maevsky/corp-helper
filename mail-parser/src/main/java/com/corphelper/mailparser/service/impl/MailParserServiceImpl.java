package com.corphelper.mailparser.service.impl;

import com.corphelper.mailparser.constant.PartStorageConstant;
import com.corphelper.mailparser.dto.MailInfoDto;
import com.corphelper.mailparser.entity.MailInfo;
import com.corphelper.mailparser.entity.Part;
import com.corphelper.mailparser.entity.PartStorage;
import com.corphelper.mailparser.exeption_handler.exception.WrongPartStorageKeyException;
import com.corphelper.mailparser.mapper.MailInfoMapper;
import com.corphelper.mailparser.repository.PartRepository;
import com.corphelper.mailparser.service.MailParserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Data
@RequiredArgsConstructor
@Service
public class MailParserServiceImpl implements MailParserService {

    private final MailInfoMapper mailInfoMapper;

    private final PartRepository partRepository;

    @Value("${file.path}")
    private String filePath;


    @SneakyThrows
    @Override
    public void pars(List<MailInfoDto> mailInfoDtoList) {

        List<MailInfo> mailInfoList = mailInfoMapper.mapToMailInfoList(mailInfoDtoList);
        parsMailInfos(mailInfoList);

    }

    private void parsMailInfos(List<MailInfo> mailInfoList) throws IOException {

        for (MailInfo mailInfo : mailInfoList) {

            File file = getFile(mailInfo);
            List<Part> parts = new ArrayList<>();

            try (FileInputStream fileStream = new FileInputStream(file);
                 Workbook workbook = WorkbookFactory.create(fileStream)) {

                iterateAllRows(parts, workbook);

            } finally {

                FileUtils.forceDelete(file);

            }

            setPartStorage(mailInfo, parts);
            partRepository.saveAll(parts);

        }
    }

    private void iterateAllRows(List<Part> parts, Workbook workbook) {

        Sheet firstSheet = workbook.getSheetAt(0);

        for (int currentRow = 0; currentRow < firstSheet.getLastRowNum(); currentRow++) {

            if (currentRow == 0) {
                continue;
            }

            Row nextRow = firstSheet.getRow(currentRow);
            Part part = iterateOneRowAndGetPart(nextRow);
            parts.add(part);

        }
    }

    private void setPartStorage(MailInfo mailInfo, List<Part> parts) {

        String storageKey = mailInfo.getFileInfo().getFileName();
        PartStorage partStorage = Optional.of(PartStorageConstant.PART_STORAGE_MAP.get(storageKey))
                .orElseThrow(() -> new WrongPartStorageKeyException("Wrong part storage key " + storageKey));
        parts.forEach(x -> x.setPartStorage(partStorage));

    }

    private Part iterateOneRowAndGetPart(Row nextRow) {

        Part part = new Part();

        for (short i = 0; i <= 4; i++) {

            Cell cell = nextRow.getCell(i);
            getCellType(cell, part, i);
            part.setCreateDate(LocalDateTime.now());
        }

        return part;
    }

    private void getCellType(Cell cell, Part part, short column) {

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

    private File getFile(MailInfo mailInfo) throws IOException {

        String fileName = getFileName(mailInfo);
        File file = new File(filePath + fileName);
        FileUtils.writeByteArrayToFile(file, mailInfo.getFileInfo().getFileBytes());

        return file;
    }

    private String getFileName(MailInfo mailInfo) {

        return mailInfo.getFileInfo().getFileName() + "." + mailInfo.getFileInfo().getExtension();
    }
}


