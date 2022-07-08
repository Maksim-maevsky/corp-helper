package com.corphelper.file_service.repository;

import com.corphelper.file_service.entity.FileInfo;

import java.util.List;

public interface FileInfoRepository {

    int[] saveAll(List<FileInfo> fileInfoList);

    int save(FileInfo fileInfo);

}
