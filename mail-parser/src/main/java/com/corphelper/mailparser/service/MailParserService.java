package com.corphelper.mailparser.service;

import com.corphelper.mailparser.dto.MailInfoDto;

import java.util.List;

public interface MailParserService {

    public void pars(List<MailInfoDto> mailInfoDtoList);

}
