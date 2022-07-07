package com.corphelper.mailservice.pojo;


import com.corphelper.mailservice.dto.FileInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailNotificationInfo {

    private String from;
    private String to;
    private String subject;
    private String message;

    private FileInfoDto fileInfoDto;

}
