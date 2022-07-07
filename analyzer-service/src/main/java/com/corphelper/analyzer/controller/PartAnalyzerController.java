package com.corphelper.analyzer.controller;

import com.corphelper.analyzer.controller.client.FileServiceClient;
import com.corphelper.analyzer.controller.client.MailServiceClient;
import com.corphelper.analyzer.dto.FileInfoDto;
import com.corphelper.analyzer.dto.RefillResponseDto;
import com.corphelper.analyzer.dto.RefillResultDto;
import com.corphelper.analyzer.service.PartAnalyzerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RestController
@RequestMapping("/analyzer-service")
@Data
@RequiredArgsConstructor
public class PartAnalyzerController {

    private final PartAnalyzerService partAnalyzerService;

    private final FileServiceClient fileServiceClient;

    private final MailServiceClient mailServiceClient;

    @GetMapping("/refilling")
    public void getDifferenceBetweenPartStorages() {

        RefillResponseDto refillResponseDto = fileServiceClient.getRefillDto(Set.of("PLASTIC", "TANGDE"), Set.of("MIKHNEVO"));
        RefillResultDto refillResultDto = partAnalyzerService.getRefillPartStorageInfo(refillResponseDto);
        FileInfoDto fileInfoDto = fileServiceClient.getExcelFile(refillResultDto);
        refillResultDto.setFileInfoDto(fileInfoDto);
        mailServiceClient.sendRefilledInfo(refillResultDto);

    }
}
