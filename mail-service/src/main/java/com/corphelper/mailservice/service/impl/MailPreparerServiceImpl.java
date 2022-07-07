package com.corphelper.mailservice.service.impl;


import com.corphelper.mailservice.constant.MailConstant;
import com.corphelper.mailservice.dto.PartInfoDto;
import com.corphelper.mailservice.pojo.MailNotificationInfo;
import com.corphelper.mailservice.dto.RefillResultDto;
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
        String message = getRefillMessage(refillResultDto.getResultPartInfoDtoList());
        String topic = String.format(MailConstant.REFILLING_MESSAGE_TOPIC, targetStores, currentStores);

        mailNotificationInfo.setSubject(topic);
        mailNotificationInfo.setTo("kantasenadas@gmail.com");
        mailNotificationInfo.setMessage(message);
        mailNotificationInfo.setFileInfoDto(refillResultDto.getFileInfoDto());

        return mailNotificationInfo;
    }

    private String getRefillMessage(List<PartInfoDto> partInfoDtoList) {

        log.info("Try to complete message text.");

        return partInfoDtoList.stream()
                .map(partInfo -> String.format(MailConstant.REFILLING_MESSAGE_PART, partInfo.getPart().getCode(),
                        partInfo.getPart().getBrand().getName(),
                        partInfo.getPart().getDescription(), partInfo.getCount()))
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
