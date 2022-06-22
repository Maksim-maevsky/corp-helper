package com.corphelper.mailparser.repository;

import com.corphelper.mailparser.entity.Part;

import java.util.List;
import java.util.Set;

public interface PartRepository {

    void delete(short storagePartId);

    public int save(Part part);

    int[] saveAll(List<Part> parts);

    List<Part> getAllByPartStorageName(Set<String> partStorageNameSet);

}
