package com.corphelper.file_service.service;

import com.corphelper.file_service.dto.RefillRequestDto;
import com.corphelper.file_service.dto.RefillResponseDto;

public interface PartService {

    RefillResponseDto getRefilledInfo(RefillRequestDto refillRequestDto);
}
