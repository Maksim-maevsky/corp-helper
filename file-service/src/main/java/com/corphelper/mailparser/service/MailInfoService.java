package com.corphelper.mailparser.service;

import com.corphelper.mailparser.entity.MailInfo;

import java.util.List;

public interface MailInfoService {

    void setLocalDateTimeAndId(List<MailInfo> mailInfoList);

    void saveAll(List<MailInfo> mailInfoList);

}
