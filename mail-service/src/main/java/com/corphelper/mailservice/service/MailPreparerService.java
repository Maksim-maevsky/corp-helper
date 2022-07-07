package com.corphelper.mailservice.service;

import com.corphelper.mailservice.pojo.MailNotificationInfo;
import com.corphelper.mailservice.dto.RefillResultDto;

public interface MailPreparerService {

    MailNotificationInfo refillPartStoragePreparation(RefillResultDto refillResultDto);
}
