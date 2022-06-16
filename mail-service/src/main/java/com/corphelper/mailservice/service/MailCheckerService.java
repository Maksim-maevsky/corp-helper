package com.corphelper.mailservice.service;

import com.corphelper.mailservice.pojo.MailInfo;

import java.util.List;

public interface MailCheckerService {

    public List<MailInfo> checkMail();
}
