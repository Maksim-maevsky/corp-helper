package com.corphelper.mailservice.service.impl;


import com.corphelper.mailservice.pojo.MailNotificationInfo;
import com.corphelper.mailservice.pojo.PartDto;
import com.corphelper.mailservice.pojo.RefillResultDto;
import com.corphelper.mailservice.service.MailPreparerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Data
@RequiredArgsConstructor
public class MailPreparerServiceImpl implements MailPreparerService {

    @Value("${spring.mail.username}")
    private String fromMail;

    @Override
    public MailNotificationInfo refillPartStoragePreparation(RefillResultDto refillResultDto) {

        MailNotificationInfo mailNotificationInfo = new MailNotificationInfo();
        mailNotificationInfo.setFrom(fromMail);

        String targetStores = getPartStorageString(refillResultDto.getTargetPartStorageName());
        String currentStores = getPartStorageString(refillResultDto.getCurrentPartStorageName());
        String message = getRefillMessage(refillResultDto.getResultPartDtoList());

        mailNotificationInfo.setSubject("Позиции которых нет на складах: " + targetStores + ". Но есть на складах: " + currentStores);
        mailNotificationInfo.setTo("avto@opox.ru");
        mailNotificationInfo.setMessage(message);

        return mailNotificationInfo;
    }

    private String getRefillMessage(List<PartDto> parts) {

        String strJoin = "";

        for (PartDto part : parts) {

            strJoin += part.getCode() + " " + part.getBrand() + " " + part.getDescription() + " - " + part.getCount() + " шт. " + "<br>";

        }

        return strJoin;

    }

    private String getPartStorageString(Set<String> storageNames) {

        StringBuilder storage = new StringBuilder();

        for (String s : storageNames) {

            storage.append(s + " ");

        }

        return storage.toString();
    }
}
