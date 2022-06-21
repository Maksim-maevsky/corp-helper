package com.corphelper.mailparser.repository;

import com.corphelper.mailparser.entity.Part;

import java.util.List;

public interface PartRepository {

    void delete(short storagePartId);

    public int save(Part part);

    int[] saveAll(List<Part> parts);

    List<Part> getAllByPartStorageName(String partStorageName);

}
