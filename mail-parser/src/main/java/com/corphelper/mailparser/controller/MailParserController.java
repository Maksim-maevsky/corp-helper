package com.corphelper.mailparser.controller;

import com.corphelper.mailparser.dto.MailInfoDto;
import com.corphelper.mailparser.dto.RefillRequestDto;
import com.corphelper.mailparser.dto.RefillResponseDto;
import com.corphelper.mailparser.service.MailParserService;
import com.corphelper.mailparser.service.PartService;
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

    private final PartService partService;


    @PostMapping
    public void pars(@RequestBody List<MailInfoDto> mailInfos) {

        mailParserService.pars(mailInfos);

    }

    @GetMapping
    public RefillResponseDto getRefillDto(RefillRequestDto refillRequestDto) {

        return partService.getRefilledInfo(refillRequestDto);
    }
}
