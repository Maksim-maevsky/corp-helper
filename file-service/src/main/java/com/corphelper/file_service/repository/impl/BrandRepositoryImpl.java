package com.corphelper.file_service.repository.impl;

import com.corphelper.file_service.entity.part.Brand;
import com.corphelper.file_service.repository.BrandRepository;
import com.corphelper.file_service.repository.mapper.BrandRowMapper;
import com.corphelper.file_service.repository.query.BrandQuery;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Data
@RequiredArgsConstructor
@Repository
public class BrandRepositoryImpl implements BrandRepository {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public Optional<Brand> getByName(String name) {


        try {

             return Optional.of(jdbcTemplate.queryForObject(String.format(BrandQuery.GET_BY_NAME), new BrandRowMapper(), name));

        } catch (EmptyResultDataAccessException exception) {

            return Optional.empty();
        }
    }
}
