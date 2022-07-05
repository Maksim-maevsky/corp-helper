package com.corphelper.mailparser.repository;

import com.corphelper.mailparser.entity.Part;

public interface PartRepository {

    public int save(Part part);

//    public Part getByCodeAndBrand(String Code, String brand);

}
