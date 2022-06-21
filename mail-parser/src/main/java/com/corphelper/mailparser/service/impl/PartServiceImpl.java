package com.corphelper.mailparser.service.impl;

import com.corphelper.mailparser.dto.RefillRequestDto;
import com.corphelper.mailparser.dto.RefillResponseDto;
import com.corphelper.mailparser.entity.Part;
import com.corphelper.mailparser.repository.PartRepository;
import com.corphelper.mailparser.service.PartService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Data
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;


    public RefillResponseDto getRefilledInfo(RefillRequestDto refillRequestDto) {

        List<Part> targetPartStorageList = partRepository.getAllByPartStorageName(refillRequestDto.getTargetPartStorageName());
        List<Part> currentPartStorageList = partRepository.getAllByPartStorageName(refillRequestDto.getCurrentPartStorageName());

        return new RefillResponseDto(targetPartStorageList, currentPartStorageList);

//        targetPartStorageList.forEach(System.out::println);
//        currentPartStorageList.forEach(System.out::println);

    }
}
