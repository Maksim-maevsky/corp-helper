package com.corphelper.mailparser.repository;

import com.corphelper.mailparser.entity.part.PartInfo;

import java.util.List;

public interface TransactionPartRepository {

    int save(PartInfo part);

    int[] saveAll(List<PartInfo> parts);

}
