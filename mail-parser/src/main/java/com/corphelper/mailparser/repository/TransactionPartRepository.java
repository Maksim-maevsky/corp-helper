package com.corphelper.mailparser.repository;

import com.corphelper.mailparser.entity.Part;

import java.util.List;

public interface TransactionPartRepository {

    int save(Part part);

    int[] saveAll(List<Part> parts);

}
