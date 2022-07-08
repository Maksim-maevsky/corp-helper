package com.corphelper.file_service.repository;

import com.corphelper.file_service.entity.part.PartInfo;

import java.util.List;
import java.util.Set;

public interface PartInfoRepository {

    void delete(short storagePartId);

    public int save(PartInfo part);

    int[] saveAll(List<PartInfo> parts);

    List<PartInfo> getAllByPartStorageName(Set<String> partStorageNameSet);

}
