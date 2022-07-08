package com.corphelper.file_service.repository;

import com.corphelper.file_service.entity.MailInfo;

import java.util.List;

public interface MailInfoRepository {

    int[] saveAll(List<MailInfo> mailInfoList);
}
