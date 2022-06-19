package com.corphelper.mailparser.repository;

import com.corphelper.mailparser.entity.FileInfo;
import com.corphelper.mailparser.entity.MailInfo;

import java.util.List;

public interface FileInfoRepository {

    int[] saveAll(List<FileInfo> fileInfoList);

}
