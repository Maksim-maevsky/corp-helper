package com.corphelper.mailparser.repository.impl;

import com.corphelper.mailparser.entity.FileInfo;
import com.corphelper.mailparser.repository.FileInfoRepository;
import com.corphelper.mailparser.repository.mapper.prepared_statement.FileInfoPreparedStatementMapper;
import com.corphelper.mailparser.repository.query.FileInfoQuery;
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
public class FileInfoRepositoryImpl implements FileInfoRepository {

    private final JdbcTemplate jdbcTemplate;

    private final FileInfoPreparedStatementMapper fileInfoPreparedStatementMapper;


    @Override
    public int save(FileInfo fileInfo) {

        return jdbcTemplate.update(FileInfoQuery.SAVE_FAIL_INFO_QUERY,
                fileInfo.getId(),
                fileInfo.getFileName(),
                fileInfo.getExtension(),
                fileInfo.getMailInfoId());
    }


    @Override
    public int[] saveAll(List<FileInfo> fileInfoList) {

        return this.jdbcTemplate.batchUpdate(FileInfoQuery.SAVE_FAIL_INFO_QUERY, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) throws SQLException {

                fileInfoPreparedStatementMapper.mapPreparedStatement(ps, fileInfoList.get(i));

            }

            @Override
            public int getBatchSize() {
                return fileInfoList.size();
            }

        });
    }
}
