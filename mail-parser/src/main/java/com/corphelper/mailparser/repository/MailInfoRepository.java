package com.corphelper.mailparser.repository;

import com.corphelper.mailparser.entity.MailInfo;

import java.util.List;

public interface MailInfoRepository {

    int[] saveAll(List<MailInfo> mailInfoList);
}
