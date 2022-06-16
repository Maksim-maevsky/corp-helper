package com.corphelper.mailparser.controller;

import com.corphelper.mailparser.dto.MailInfoDto;
import com.corphelper.mailparser.service.MailParserService;
import com.google.inject.internal.cglib.proxy.$CallbackFilter;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
