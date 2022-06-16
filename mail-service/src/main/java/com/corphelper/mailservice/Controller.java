package com.corphelper.mailservice;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@Data
@RequiredArgsConstructor
public class Controller {

    private final MailCheckerService mailCheckerService;

    @GetMapping("/check")
    public void checkNewMessages() {

        mailCheckerService.checkMail();

    }
}
