package com.corphelper.file_service.repository;

import com.corphelper.file_service.entity.part.PartInfo;

import java.util.List;

public interface TransactionPartRepository {

    int save(PartInfo part);

    int[] saveAll(List<PartInfo> parts);

}
