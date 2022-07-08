package com.corphelper.file_service.repository.impl;

import com.corphelper.file_service.entity.part.Part;
import com.corphelper.file_service.repository.PartRepository;
import com.corphelper.file_service.repository.mapper.prepared_statement.PartInfoPreparedStatementMapper;
import com.corphelper.file_service.repository.query.PartQuery;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Repository
public class PartRepositoryImpl implements PartRepository {

    private final JdbcTemplate jdbcTemplate;

    private final PartInfoPreparedStatementMapper partInfoPreparedStatementMapper;


    @Override
    public int save(Part part) {

        return jdbcTemplate.update(PartQuery.SAVE_PART_QUERY, part.getId(), part.getCode(), part.getDescription(),
                part.getBrand().getId(), part.getCreateDate());
    }


    @Override
    public Optional<UUID> getIdByCodeAndBrand(String code, String brandName) {

        try {

            return jdbcTemplate.queryForObject(
                    String.format(PartQuery.GET_PART_BY_CODE_AND_STORAGE_NAME, code, brandName), Optional.class);

        } catch (EmptyResultDataAccessException exception) {

            return Optional.empty();
        }
    }
}
