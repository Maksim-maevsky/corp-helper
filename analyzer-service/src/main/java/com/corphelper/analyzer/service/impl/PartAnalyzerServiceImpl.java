package com.corphelper.analyzer.service.impl;


import com.corphelper.analyzer.dto.PartDto;
import com.corphelper.analyzer.dto.RefillResponseDto;
import com.corphelper.analyzer.dto.RefillResultDto;
import com.corphelper.analyzer.service.PartAnalyzerService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Data
@Slf4j
public class PartAnalyzerServiceImpl implements PartAnalyzerService {


    @Override
    public RefillResultDto getRefillPartStorageInfo(RefillResponseDto refillResponseDto) {

        List<PartDto> resultPartDtoList = getResultPartDtoList(refillResponseDto);

        return  getRefillResultDto(refillResponseDto, resultPartDtoList);
    }

    private RefillResultDto getRefillResultDto(RefillResponseDto refillResponseDto, List<PartDto> resultPartDtoList) {

        RefillResultDto refillResultDto = new RefillResultDto();

        refillResultDto.setResultPartDtoList(resultPartDtoList);
        refillResultDto.setCurrentPartStorageName(refillResponseDto.getCurrentPartStorageInfoDto().getPartStorageName());
        refillResultDto.setTargetPartStorageName(refillResponseDto.getTargetPartStorageInfoDto().getPartStorageName());

        return refillResultDto;
    }

    private List<PartDto> getResultPartDtoList(RefillResponseDto refillResponseDto) {

        List<PartDto> resultPartDtoList = new ArrayList<>();

        for (PartDto currentPart : refillResponseDto.getCurrentPartStorageInfoDto().getPartList()){

            iterateByTargetPartDtoListAndSetToResultPartDtoList(refillResponseDto, resultPartDtoList, currentPart);

        }

        return resultPartDtoList;
    }

    private void iterateByTargetPartDtoListAndSetToResultPartDtoList(RefillResponseDto refillResponseDto, List<PartDto> resultPartDtoList, PartDto currentPart) {

        int matchCount = 0;

        for(PartDto targetPart : refillResponseDto.getTargetPartStorageInfoDto().getPartList()){

            if (currentPart.getCode().equals(targetPart.getCode())){

                matchCount++;

            }
        }

        if(matchCount == 0){

            resultPartDtoList.add(currentPart);

        }
    }
}
