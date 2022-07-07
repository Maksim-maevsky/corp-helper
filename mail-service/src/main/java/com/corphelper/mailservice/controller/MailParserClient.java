package com.corphelper.mailservice.controller;

import com.corphelper.mailservice.pojo.MailInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@FeignClient("FILE-SERVICE")
public interface MailParserClient {

    @PostMapping("/file-service")
    void parse(List<MailInfo> mailInfos);

}