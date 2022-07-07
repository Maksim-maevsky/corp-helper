package com.corphelper.mailparser.repository;

import com.corphelper.mailparser.entity.part.Brand;

import java.util.Optional;

public interface BrandRepository {

    Optional<Brand> getByName(String name);

}
