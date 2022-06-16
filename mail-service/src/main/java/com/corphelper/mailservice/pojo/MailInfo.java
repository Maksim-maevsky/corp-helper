package com.corphelper.mailservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {

    private String subject;

    private String from;

    private FileInfo fileInfo;

    private LocalDateTime dateTime;

}
