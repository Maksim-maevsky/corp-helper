package com.corphelper.file_service.service;

import com.corphelper.file_service.dto.FileInfoDto;
import com.corphelper.file_service.dto.RefillResultDto;

public interface FileCreatorService {

    FileInfoDto getFile(RefillResultDto refillResultDto);

}
