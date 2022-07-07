package com.corphelper.mailparser.service;

import com.corphelper.mailparser.dto.MailInfoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileParserService {

    public void pars(List<MailInfoDto> mailInfoDtoList);

}
