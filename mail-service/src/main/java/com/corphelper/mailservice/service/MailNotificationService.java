package com.corphelper.mailservice.service;

import com.corphelper.mailservice.pojo.MailNotificationInfo;

public interface MailNotificationService {

    void send(MailNotificationInfo mailNotificationInfo);
}
