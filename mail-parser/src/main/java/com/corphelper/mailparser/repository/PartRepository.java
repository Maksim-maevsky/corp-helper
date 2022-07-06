package com.corphelper.mailparser.repository;

import com.corphelper.mailparser.entity.part.Part;

import java.util.Optional;
import java.util.UUID;

public interface PartRepository {

    int save(Part part);

    Optional<UUID> getIdByCodeAndBrand(String Code, String brand);

}
