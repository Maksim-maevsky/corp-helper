package com.corphelper.mailservice.service.impl;

import com.corphelper.mailservice.pojo.MailNotificationInfo;
import com.corphelper.mailservice.service.MailNotificationService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Data
@Slf4j
@RequiredArgsConstructor
@Service
public class MailNotificationServiceImpl implements MailNotificationService {


    private final JavaMailSender emailSender;


    @Override
    public void send(MailNotificationInfo mailNotificationInfo) {

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        trySetDataMimeMessage(mailNotificationInfo, mimeMessage);
        emailSender.send(mimeMessage);
    }

    private void trySetDataMimeMessage(MailNotificationInfo notification, MimeMessage mimeMessage) {

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        try {

            helper.setText(notification.getMessage(), true);
            helper.setTo(notification.getTo());
            helper.setSubject(notification.getSubject());
            helper.setFrom(notification.getFrom());

        } catch (MessagingException e) {

            e.printStackTrace();

        }
    }


}
