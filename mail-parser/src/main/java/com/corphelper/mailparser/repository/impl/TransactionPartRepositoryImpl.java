package com.corphelper.mailparser.repository.impl;

import com.corphelper.mailparser.entity.part.PartInfo;
import com.corphelper.mailparser.repository.TransactionPartRepository;
import com.corphelper.mailparser.repository.mapper.prepared_statement.PartInfoPreparedStatementMapper;
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

    private final PartInfoPreparedStatementMapper partInfoPreparedStatementMapper;


    @Override
    public int save(PartInfo part) {

        return jdbcTemplate.update(TransactionPartQuery.SAVE_TRANSACTION_PART_QUERY, part.getId(),
                part.getCount(), part.getPartStorageId(), part.getPartId(), part.getCreateDate());
    }

    @Override
    public int[] saveAll(List<PartInfo> partInfos) {

        return this.jdbcTemplate.batchUpdate(TransactionPartQuery.SAVE_TRANSACTION_PART_QUERY, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) throws SQLException {

                partInfoPreparedStatementMapper.mapPreparedStatement(ps, partInfos.get(i));

            }

            @Override
            public int getBatchSize() {
                return partInfos.size();
            }

        });
    }
}
