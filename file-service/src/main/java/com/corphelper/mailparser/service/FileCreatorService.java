package com.corphelper.mailparser.service;

import com.corphelper.mailparser.dto.FileInfoDto;
import com.corphelper.mailparser.dto.RefillResultDto;

public interface FileCreatorService {

    FileInfoDto getFile(RefillResultDto refillResultDto);

}
