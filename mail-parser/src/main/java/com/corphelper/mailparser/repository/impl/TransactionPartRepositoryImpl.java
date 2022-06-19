package com.corphelper.mailparser.repository.impl;

import com.corphelper.mailparser.entity.Part;
import com.corphelper.mailparser.repository.TransactionPartRepository;
import com.corphelper.mailparser.repository.mapper.prepared_statement.TransactionPartPreparedStatementMapper;
import com.corphelper.mailparser.repository.query.PartQuery;
import com.corphelper.mailparser.repository.query.TransactionPartQuery;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Data
@RequiredArgsConstructor
@Repository
public class TransactionPartRepositoryImpl implements TransactionPartRepository {

    private final JdbcTemplate jdbcTemplate;

    private final TransactionPartPreparedStatementMapper transactionPartPreparedStatementMapper;


    @Override
    public int save(Part part) {

        return jdbcTemplate.update(TransactionPartQuery.SAVE_TRANSACTION_PART_QUERY, part.getId(),
                part.getCode(), part.getDescription(), part.getCreateDate(),
                part.getBrand(), part.getCount(), part.getPartStorageId());
    }

    @Override
    public int[] saveAll(List<Part> parts) {

        return this.jdbcTemplate.batchUpdate(PartQuery.SAVE_PART_QUERY, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) throws SQLException {

                transactionPartPreparedStatementMapper.mapPreparedStatement(ps, parts.get(i));

            }

            @Override
            public int getBatchSize() {
                return parts.size();
            }

        });
    }
}
