package com.corphelper.mailparser.controller;

import com.corphelper.mailparser.dto.MailInfoDto;
import com.corphelper.mailparser.service.MailParserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mail-parser")
@Data
@RequiredArgsConstructor
public class MailParserController {

    private final MailParserService mailParserService;


    @PostMapping
    public void pars(@RequestBody List<MailInfoDto> mailInfos) {

        mailParserService.pars(mailInfos);

    }
}
