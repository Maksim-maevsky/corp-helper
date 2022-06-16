package com.corphelper.mailparser.service.impl;

import com.corphelper.mailparser.dto.MailInfoDto;
import com.corphelper.mailparser.service.MailParserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class MailParserServiceImpl implements MailParserService {

    @Override
    public void pars(List<MailInfoDto> mailInfoDtoList){



    }

}
