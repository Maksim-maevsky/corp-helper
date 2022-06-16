package com.corphelper.mailparser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfoDto {

    private String subject;

    private String from;

    private FileInfoDto fileInfo;

    private LocalDateTime dateTime;

}
