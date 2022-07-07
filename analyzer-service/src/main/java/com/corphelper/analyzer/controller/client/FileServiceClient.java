package com.corphelper.analyzer.controller.client;

import com.corphelper.analyzer.dto.FileInfoDto;
import com.corphelper.analyzer.dto.RefillResponseDto;
import com.corphelper.analyzer.dto.RefillResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@FeignClient("FILE-SERVICE")
public interface FileServiceClient {

    @GetMapping(value = "/file-service/refilling")
    RefillResponseDto getRefillDto(@RequestParam("target") Set<String> target, @RequestParam("current") Set<String> current);

    @PostMapping(value = "/file-service/file")
    FileInfoDto getExcelFile(@RequestBody RefillResultDto refillResultDto);

}