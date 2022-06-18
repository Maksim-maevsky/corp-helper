package com.corphelper.mailparser.repository;

import com.corphelper.mailparser.entity.MailInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MailInfoRepository extends JpaRepository<MailInfo, UUID> {
}
