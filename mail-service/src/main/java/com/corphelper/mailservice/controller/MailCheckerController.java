package com.corphelper.mailservice.controller;

import com.corphelper.mailservice.pojo.MailInfo;
import com.corphelper.mailservice.service.MailCheckerService;
import com.corphelper.mailservice.service.impl.MailCheckerServiceImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/mail")
@Data
@RequiredArgsConstructor
public class MailCheckerController {

    private final MailCheckerService mailCheckerService;

    private final MailParserClient mailParserClient;

    @GetMapping("/check")
    public void checkNewMessages() {

        List<MailInfo> mailInfoList = mailCheckerService.checkMail();
        mailParserClient.parse(mailInfoList);

    }
}
