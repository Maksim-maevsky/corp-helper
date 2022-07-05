package com.corphelper.mailparser.repository.query;

public final class TransactionPartQuery {

    private TransactionPartQuery(){}

    public static final String SAVE_TRANSACTION_PART_QUERY = "INSERT INTO transaction_parts(id, count, " +
            "part_storage_id, part_id, create_date) VALUES(?,?,?,?,?)";

}
