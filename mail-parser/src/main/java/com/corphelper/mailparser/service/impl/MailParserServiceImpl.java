package com.corphelper.mailparser.service.impl;

import com.corphelper.mailparser.dto.MailInfoDto;
import com.corphelper.mailparser.entity.MailInfo;
import com.corphelper.mailparser.mapper.MailInfoMapper;
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
import java.util.Iterator;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class MailParserServiceImpl implements MailParserService {

    private final MailInfoMapper mailInfoMapper;

    @Value("${file.path}")
    private String filePath;

    @SneakyThrows
    @Override
    public void pars(List<MailInfoDto> mailInfoDtoList) {

        List<MailInfo> mailInfoList = mailInfoMapper.mapToMailInfoList(mailInfoDtoList);

        for (MailInfo mailInfo : mailInfoList) {

            String fileName = getFileName(mailInfo);
            File file = getFile(mailInfo, fileName);

            try (FileInputStream fileStream = new FileInputStream(file);
                 Workbook workbook = WorkbookFactory.create(fileStream)) {

                Sheet firstSheet = workbook.getSheetAt(0);
                Iterator<Row> iterator = firstSheet.iterator();

                while (iterator.hasNext()) {

                    Row nextRow = iterator.next();
                    iterateRow(nextRow);

                }

            } finally {

                FileUtils.forceDelete(file);

            }
        }
    }

    private void iterateRow(Row nextRow) {

        for (int i = 0; i <= 4; i++) {

            Cell cell = nextRow.getCell(i);
            getCellType(cell);

        }
    }

    private void getCellType(Cell cell) {

        if (cell != null) {

            switch (cell.getCellType()) {

                case STRING:
                    System.out.print(cell.getStringCellValue());
                    break;

                case BOOLEAN:
                    System.out.print(cell.getBooleanCellValue());
                    break;

                case NUMERIC:
                    System.out.print(cell.getNumericCellValue());
                    break;

            }
        } else {

            return;
        }
    }

    private File getFile(MailInfo mailInfo, String fileName) throws IOException {

        File file = new File(filePath + fileName);
        FileUtils.writeByteArrayToFile(file, mailInfo.getFileInfo().getFileBytes());

        return file;
    }

    private String getFileName(MailInfo mailInfo) {

        return mailInfo.getFileInfo().getFileName() + "." + mailInfo.getFileInfo().getExtension();
    }
}


