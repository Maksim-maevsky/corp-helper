package com.corphelper.schedulerservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("ANALYZER-SERVICE")
public interface AnalyzerClient {

    @GetMapping("/analyzer-service/refilling")
    void getDifferenceBetweenPartStorages();

}
