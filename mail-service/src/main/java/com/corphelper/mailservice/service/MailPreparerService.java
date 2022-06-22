package com.corphelper.mailservice.service;

import com.corphelper.mailservice.pojo.MailNotificationInfo;
import com.corphelper.mailservice.pojo.RefillResultDto;

public interface MailPreparerService {

    MailNotificationInfo refillPartStoragePreparation(RefillResultDto refillResultDto);
}
