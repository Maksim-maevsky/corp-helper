package com.corphelper.schedulerservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("MAIL-SERVICE")
public interface MailServiceClient {

    @GetMapping("/mail/check")
    void checkEmail();

}
