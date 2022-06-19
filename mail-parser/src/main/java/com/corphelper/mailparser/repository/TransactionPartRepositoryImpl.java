package com.corphelper.mailparser.repository;

import com.corphelper.mailparser.entity.Part;
import com.corphelper.mailparser.repository.query.TransactionPartQuery;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Data
@RequiredArgsConstructor
@Repository
public class TransactionPartRepositoryImpl {

    private final JdbcTemplate jdbcTemplate;


    public int save(Part part) {

        return jdbcTemplate.update(TransactionPartQuery.SAVE_TRANSACTION_PART_QUERY, part.getId(),
                part.getCode(), part.getDescription(), part.getCreateDate(),
                part.getBrand(), part.getCount(), part.getPartStorageId());
    }
}
