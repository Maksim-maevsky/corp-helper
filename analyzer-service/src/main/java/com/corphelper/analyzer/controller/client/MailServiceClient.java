package com.corphelper.analyzer.controller.client;


import com.corphelper.analyzer.dto.RefillResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("MAIL-SERVICE")
public interface MailServiceClient {

    @PostMapping(value = "/mail")
    void sendRefilledInfo(@RequestBody RefillResultDto refillResultDto);

}
