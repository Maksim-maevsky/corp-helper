package com.corphelper.mailparser.service.impl;

import com.corphelper.mailparser.constant.PartStorageConstant;
import com.corphelper.mailparser.dto.PartStorageInfoDto;
import com.corphelper.mailparser.dto.RefillRequestDto;
import com.corphelper.mailparser.dto.RefillResponseDto;
import com.corphelper.mailparser.entity.Part;
import com.corphelper.mailparser.exeption_handler.exception.WrongPartStorageNameException;
import com.corphelper.mailparser.repository.PartRepository;
import com.corphelper.mailparser.service.PartService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@Slf4j
@Data
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;


    public RefillResponseDto getRefilledInfo(RefillRequestDto refillRequestDto) {

        log.info("Create RefillResponseDto by part storage names ");

        refillRequestDto.getCurrentPartStorageNameSet().forEach(this::checkPartStorageName);
        refillRequestDto.getTargetPartStorageNameSet().forEach(this::checkPartStorageName);

        List<Part> targetPartStorageList = partRepository.getAllByPartStorageName(refillRequestDto.getTargetPartStorageNameSet());
        List<Part> currentPartStorageList = partRepository.getAllByPartStorageName(refillRequestDto.getCurrentPartStorageNameSet());

        PartStorageInfoDto targetPartStorageInfoDto = getPartStorageInfoDto(refillRequestDto.getTargetPartStorageNameSet(), targetPartStorageList);
        PartStorageInfoDto currentPartStorageInfoDto = getPartStorageInfoDto(refillRequestDto.getCurrentPartStorageNameSet(), currentPartStorageList);

        return new RefillResponseDto(targetPartStorageInfoDto, currentPartStorageInfoDto);
    }

    private PartStorageInfoDto getPartStorageInfoDto(Set<String> partStorageName, List<Part> targetPartStorageList) {

        PartStorageInfoDto partStorageInfoDto = new PartStorageInfoDto();

        partStorageInfoDto.setPartStorageNameSet(partStorageName);
        partStorageInfoDto.setPartList(targetPartStorageList);

        return partStorageInfoDto;
    }

    private void checkPartStorageName(String partStorageName) {

        if (partStorageName != null) {

            if (!PartStorageConstant.PART_STORAGE_NAME_SET.contains(partStorageName)) {

                throw new WrongPartStorageNameException("Wrong part storage name " + partStorageName);

            }

        } else {

            throw new WrongPartStorageNameException("Part storage name is null");
        }
    }
}
