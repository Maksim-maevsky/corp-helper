package com.corphelper.analyzer.service;

import com.corphelper.analyzer.dto.PartDto;
import com.corphelper.analyzer.dto.RefillResponseDto;
import com.corphelper.analyzer.dto.RefillResultDto;

import java.util.List;

public interface PartAnalyzerService {

    RefillResultDto getRefillPartStorageInfo(RefillResponseDto refillResponseDto);
}
