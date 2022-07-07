package com.corphelper.mailparser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfoDto {

    private String subject;

    private String from;

    private List<FileInfoDto> fileInfoList;

    private LocalDateTime dateTime;

}
