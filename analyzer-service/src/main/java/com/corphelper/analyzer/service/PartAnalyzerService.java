package com.corphelper.analyzer.service;

import com.corphelper.analyzer.dto.RefillResponseDto;
import com.corphelper.analyzer.dto.RefillResultDto;

public interface PartAnalyzerService {

    RefillResultDto getRefillPartStorageInfo(RefillResponseDto refillResponseDto);
}
