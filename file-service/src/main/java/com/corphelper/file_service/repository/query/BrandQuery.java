package com.corphelper.file_service.repository.query;

public final class BrandQuery {

    private BrandQuery(){}

    public static final String GET_BY_NAME = "SELECT * FROM brands WHERE brands.name = ?";

}
