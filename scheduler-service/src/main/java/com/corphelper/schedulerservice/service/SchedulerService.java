package com.corphelper.schedulerservice.service;


import com.corphelper.schedulerservice.client.AnalyzerClient;
import com.corphelper.schedulerservice.client.MailServiceClient;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Data
@Slf4j
@RequiredArgsConstructor
public class SchedulerService {

    private final MailServiceClient mailServiceClient;
    private final AnalyzerClient analyzerClient;


    @Scheduled(cron = "${cron.mail.check}")
    private void checkEmail(){

        log.info("Execute check email.");

        mailServiceClient.checkEmail();

    }


    @Scheduled(cron = "${cron.analyzer.refill}")
    private void compareStorages(){

        log.info("Execute refill storages.");

        analyzerClient.getDifferenceBetweenPartStorages();

    }
}
