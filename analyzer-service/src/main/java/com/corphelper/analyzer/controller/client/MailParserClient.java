package com.corphelper.analyzer.controller.client;

import com.corphelper.analyzer.dto.RefillResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@FeignClient("MAIL-PARSER-SERVICE")
public interface MailParserClient {

    @GetMapping(value = "/mail-parser/refilling")
    RefillResponseDto getRefillDto(@RequestParam("target") Set<String> target, @RequestParam("current") Set<String> current);

}