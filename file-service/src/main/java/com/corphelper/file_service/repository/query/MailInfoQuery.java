package com.corphelper.file_service.repository.query;

public final class MailInfoQuery {

    private MailInfoQuery(){}

    public static final String SAVE_MAIL_INFO_QUERY = "INSERT INTO mail_infos(id, subject, from_full_name, date) VALUES(?,?,?,?)";

}
