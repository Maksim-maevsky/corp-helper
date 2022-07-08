package com.corphelper.file_service.mapper;


import com.corphelper.file_service.dto.MailInfoDto;
import com.corphelper.file_service.entity.MailInfo;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MailInfoMapper {

    @Named(value = "mailInfoDto")
    MailInfoDto mapToMailInfoDto(MailInfo mailInfo);

    @Named(value = "mailInfo")
    MailInfo mapToMailInfo(MailInfoDto mailInfoDto);

    @IterableMapping(qualifiedByName = "mailInfoDto")
    List<MailInfoDto> mapToMailInfoDtoList(List<MailInfo> mailInfoList);

    @IterableMapping(qualifiedByName = "mailInfo")
    List<MailInfo> mapToMailInfoList(List<MailInfoDto> mailInfoDtoList);

}