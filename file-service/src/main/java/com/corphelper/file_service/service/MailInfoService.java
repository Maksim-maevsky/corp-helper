package com.corphelper.file_service.service;

import com.corphelper.file_service.entity.MailInfo;

import java.util.List;

public interface MailInfoService {

    void setLocalDateTimeAndId(List<MailInfo> mailInfoList);

    void saveAll(List<MailInfo> mailInfoList);

}
