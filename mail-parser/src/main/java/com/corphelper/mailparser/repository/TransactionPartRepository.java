package com.corphelper.mailparser.repository;

import com.corphelper.mailparser.entity.Part;

public interface TransactionPartRepository {

    public int save(Part part);
}
