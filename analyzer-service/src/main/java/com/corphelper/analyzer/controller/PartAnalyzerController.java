package com.corphelper.analyzer.controller;

import com.corphelper.analyzer.controller.client.MailParserClient;
import com.corphelper.analyzer.controller.client.MailServiceClient;
import com.corphelper.analyzer.dto.RefillResponseDto;
import com.corphelper.analyzer.dto.RefillResultDto;
import com.corphelper.analyzer.service.PartAnalyzerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/analyzer-service")
@Data
@RequiredArgsConstructor
public class PartAnalyzerController {

    private final PartAnalyzerService partAnalyzerService;

    private final MailParserClient mailParserClient;

    private final MailServiceClient mailServiceClient;

    @GetMapping("/refilling")
    public void getDifferenceBetweenPartStorages() {

        RefillResponseDto refillResponseDto = mailParserClient.getRefillDto(Set.of("PLASTIC", "TANGDE"), Set.of("MIKHNEVO"));
        RefillResultDto refillResultDto = partAnalyzerService.getRefillPartStorageInfo(refillResponseDto);
        mailServiceClient.sendRefilledInfo(refillResultDto);

    }
}
