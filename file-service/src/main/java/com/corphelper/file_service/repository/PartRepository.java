package com.corphelper.file_service.repository;

import com.corphelper.file_service.entity.part.Part;

import java.util.Optional;
import java.util.UUID;

public interface PartRepository {

    int save(Part part);

    Optional<UUID> getIdByCodeAndBrand(String Code, String brand);

}
