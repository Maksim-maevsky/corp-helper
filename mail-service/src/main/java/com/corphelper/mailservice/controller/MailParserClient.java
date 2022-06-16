package com.corphelper.mailservice.controller;

import com.corphelper.mailservice.pojo.MailInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@FeignClient("MAIL-PARSER-SERVICE")
public interface MailParserClient {

    @PostMapping("/mail-parser")
    void parse(List<MailInfo> mailInfos);

}