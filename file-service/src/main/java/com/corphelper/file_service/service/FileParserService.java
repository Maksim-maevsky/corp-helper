package com.corphelper.file_service.service;

import com.corphelper.file_service.dto.MailInfoDto;

import java.util.List;

public interface FileParserService {

    public void pars(List<MailInfoDto> mailInfoDtoList);

}
