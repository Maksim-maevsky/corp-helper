package com.corphelper.file_service.controller;

import com.corphelper.file_service.dto.*;
import com.corphelper.file_service.service.FileCreatorService;
import com.corphelper.file_service.service.FileParserService;
import com.corphelper.file_service.service.PartService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/file-service")
@Data
@RequiredArgsConstructor
public class FileServiceController {

    private final FileParserService fileParserService;

    private final FileCreatorService fileCreatorService;

    private final PartService partService;


    @PostMapping
    public void pars(@RequestBody List<MailInfoDto> mailInfos) {

        fileParserService.pars(mailInfos);

    }

    @GetMapping("/refilling")
    public RefillResponseDto getRefillDto(@RequestParam("target") Set<String> target, @RequestParam("current") Set<String> current) {

        RefillRequestDto refillRequestDto = new RefillRequestDto();
        refillRequestDto.setCurrentPartStorageNameSet(current);
        refillRequestDto.setTargetPartStorageNameSet(target);

        return partService.getRefilledInfo(refillRequestDto);
    }

    @PostMapping("/file")
    public FileInfoDto getFile(@RequestBody RefillResultDto refillResultDto){

        return fileCreatorService.getFile(refillResultDto);
    }
}
