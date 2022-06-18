package com.corphelper.mailservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {

    private String subject;

    private String from;

    private List<FileInfo> fileInfoList;

    private LocalDateTime dateTime;

}
