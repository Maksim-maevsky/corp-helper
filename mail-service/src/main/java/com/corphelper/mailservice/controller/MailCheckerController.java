package com.corphelper.mailservice.controller;

import com.corphelper.mailservice.MailNotificationPreparerFacade;
import com.corphelper.mailservice.pojo.MailInfo;
import com.corphelper.mailservice.dto.RefillResultDto;
import com.corphelper.mailservice.service.MailCheckerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mail")
@Data
@RequiredArgsConstructor
public class MailCheckerController {

    private final MailCheckerService mailCheckerService;

    private final MailParserClient mailParserClient;

    private final MailNotificationPreparerFacade mailNotificationPreparerFacade;


    @GetMapping("/check")
    public void checkNewMessages() {

        List<MailInfo> mailInfoList = mailCheckerService.checkMail();
        mailParserClient.parse(mailInfoList);

    }

    @PostMapping
    public void sendRefilledInfo(@RequestBody RefillResultDto refillResultDto) {

        mailNotificationPreparerFacade.sendRefilledInfo(refillResultDto);

    }
}
