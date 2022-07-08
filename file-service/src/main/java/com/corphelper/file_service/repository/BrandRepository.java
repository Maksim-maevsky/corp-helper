package com.corphelper.file_service.repository;

import com.corphelper.file_service.entity.part.Brand;

import java.util.Optional;

public interface BrandRepository {

    Optional<Brand> getByName(String name);

}
