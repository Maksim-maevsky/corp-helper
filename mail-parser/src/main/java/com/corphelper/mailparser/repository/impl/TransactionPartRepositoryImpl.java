package com.corphelper.mailparser.repository.impl;

import com.corphelper.mailparser.entity.Part;
import com.corphelper.mailparser.repository.TransactionPartRepository;
import com.corphelper.mailparser.repository.query.TransactionPartQuery;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Data
@RequiredArgsConstructor
@Repository
public class TransactionPartRepositoryImpl implements TransactionPartRepository {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public int save(Part part) {

        return jdbcTemplate.update(TransactionPartQuery.SAVE_TRANSACTION_PART_QUERY, part.getId(),
                part.getCode(), part.getDescription(), part.getCreateDate(),
                part.getBrand(), part.getCount(), part.getPartStorageId());
    }
}
