package com.corphelper.mailservice.service.impl;


import com.corphelper.mailservice.constant.MailConstant;
import com.corphelper.mailservice.pojo.MailNotificationInfo;
import com.corphelper.mailservice.pojo.PartDto;
import com.corphelper.mailservice.pojo.RefillResultDto;
import com.corphelper.mailservice.service.MailPreparerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Data
@Slf4j
@RequiredArgsConstructor
public class MailPreparerServiceImpl implements MailPreparerService {

    @Value("${spring.mail.username}")
    private String fromMail;

    @Override
    public MailNotificationInfo refillPartStoragePreparation(RefillResultDto refillResultDto) {

        log.info("Try to get MailNotificationInfo.");

        MailNotificationInfo mailNotificationInfo = new MailNotificationInfo();
        mailNotificationInfo.setFrom(fromMail);

        String targetStores = getPartStorageString(refillResultDto.getTargetPartStorageName());
        String currentStores = getPartStorageString(refillResultDto.getCurrentPartStorageName());
        String message = getRefillMessage(refillResultDto.getResultPartDtoList());
        String topic = String.format(MailConstant.REFILLING_MESSAGE_TOPIC, targetStores, currentStores);

        mailNotificationInfo.setSubject(topic);
        mailNotificationInfo.setTo("kantasenadas@gmail.com");
        mailNotificationInfo.setMessage(message);

        return mailNotificationInfo;
    }

    private String getRefillMessage(List<PartDto> parts) {

        log.info("Try to complete message text.");

        return parts.stream()
                .map(part -> String.format(MailConstant.REFILLING_MESSAGE_PART, part.getCode(), part.getBrand(),
                        part.getDescription(), part.getCount()))
                .collect(Collectors.joining(MailConstant.STRING_JOINER_DELIMITER));
    }

    private String getPartStorageString(Set<String> storageNames) {

        StringBuilder storage = new StringBuilder();

        for (String s : storageNames) {

            storage.append(s + " ");

        }

        return storage.toString();
    }
}
