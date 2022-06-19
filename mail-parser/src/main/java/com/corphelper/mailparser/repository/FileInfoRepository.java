package com.corphelper.mailparser.repository;

import com.corphelper.mailparser.entity.FileInfo;
import com.corphelper.mailparser.entity.MailInfo;
import com.corphelper.mailparser.entity.Part;

import java.util.List;

public interface FileInfoRepository {

    int[] saveAll(List<FileInfo> fileInfoList);

    int save(FileInfo fileInfo);

}
