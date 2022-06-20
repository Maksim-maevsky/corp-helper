package com.corphelper.mailparser.service;

import com.corphelper.mailparser.dto.RefillRequestDto;
import com.corphelper.mailparser.dto.RefillResponseDto;

public interface PartService {

    RefillResponseDto getRefilledInfo(RefillRequestDto refillRequestDto);
}
