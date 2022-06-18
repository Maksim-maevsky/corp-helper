package com.corphelper.mailparser.repository;

import com.corphelper.mailparser.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PartRepository extends JpaRepository<Part, UUID> {
}
