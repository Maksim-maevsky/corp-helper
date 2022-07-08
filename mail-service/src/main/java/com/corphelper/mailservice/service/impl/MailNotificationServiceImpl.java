package com.corphelper.mailservice.service.impl;

import com.corphelper.mailservice.pojo.MailNotificationInfo;
import com.corphelper.mailservice.service.MailNotificationService;
import com.corphelper.mailservice.util.MimeMessageCreator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Data
@Slf4j
@RequiredArgsConstructor
@Service
public class MailNotificationServiceImpl implements MailNotificationService {

    private final JavaMailSender emailSender;

    private final MimeMessageCreator mimeMessageCreator;


    @SneakyThrows
    @Override
    public void send(MailNotificationInfo mailNotificationInfo) {

        log.info("Try to send message.");

        MimeMessage mimeMessage = mimeMessageCreator.getMimeMessage(mailNotificationInfo);

        emailSender.send(mimeMessage);

        log.info("Message was sent");

    }
}
