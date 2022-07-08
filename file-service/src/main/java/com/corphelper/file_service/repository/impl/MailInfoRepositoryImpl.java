package com.corphelper.file_service.repository.impl;

import com.corphelper.file_service.entity.MailInfo;
import com.corphelper.file_service.repository.MailInfoRepository;
import com.corphelper.file_service.repository.mapper.prepared_statement.MailInfoPreparedStatementMapper;
import com.corphelper.file_service.repository.query.MailInfoQuery;
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
public class MailInfoRepositoryImpl implements MailInfoRepository {

    private final JdbcTemplate jdbcTemplate;

    private final MailInfoPreparedStatementMapper mailInfoPreparedStatementMapper;


    @Override
    public int[] saveAll(List<MailInfo> mailInfoList) {

        return this.jdbcTemplate.batchUpdate(MailInfoQuery.SAVE_MAIL_INFO_QUERY, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) throws SQLException {

                mailInfoPreparedStatementMapper.mapPreparedStatement(ps, mailInfoList.get(i));

            }

            @Override
            public int getBatchSize() {
                return mailInfoList.size();
            }

        });
    }
}
