package com.corphelper.mailparser.service.impl;


import com.corphelper.mailparser.entity.MailInfo;
import com.corphelper.mailparser.repository.MailInfoRepository;
import com.corphelper.mailparser.service.MailInfoService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Data
@Slf4j
@RequiredArgsConstructor
public class MailInfoServiceImpl implements MailInfoService {

    private final MailInfoRepository mailInfoRepository;

    @Override
    public void setLocalDateTimeAndId(List<MailInfo> mailInfoList) {

        log.info("Set date and Id to mailInfoList.");

        mailInfoList.forEach(x -> {
            x.setDateTime(LocalDateTime.now());
            x.setId(UUID.randomUUID());
        });
    }


    public void saveAll( List<MailInfo> mailInfoList){

        log.info("Save mailInfoList.");

        mailInfoRepository.saveAll(mailInfoList);

    }
}
