package com.corphelper.mailservice;

import com.corphelper.mailservice.dto.RefillResultDto;
import com.corphelper.mailservice.pojo.MailNotificationInfo;
import com.corphelper.mailservice.service.MailNotificationService;
import com.corphelper.mailservice.service.MailPreparerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class MailNotificationPreparerFacade {

    private final MailNotificationService mailNotificationService;

    private final MailPreparerService mailPreparerService;


    public void sendRefilledInfo(RefillResultDto refillResultDto) {

        MailNotificationInfo mailNotificationInfo = mailPreparerService.refillPartStoragePreparation(refillResultDto);

        mailNotificationService.send(mailNotificationInfo);

    }
}
