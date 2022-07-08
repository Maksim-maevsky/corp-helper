package com.corphelper.file_service.mapper;


import com.corphelper.file_service.dto.FileInfoDto;
import com.corphelper.file_service.entity.FileInfo;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import java.util.*;

@Mapper(componentModel = "spring")
public interface FileInfoMapper {

    @Named(value = "fileInfoDto")
    FileInfoDto mapToFileInfoDto(FileInfo fileInfo);

    @Named(value = "fileInfo")
    FileInfo mapToFileInfo(FileInfoDto fileInfoDto);

    @IterableMapping(qualifiedByName = "fileInfoDto")
    List<FileInfoDto> mapToFileInfoDtoList(List<FileInfo> fileInfoList);

    @IterableMapping(qualifiedByName = "fileInfo")
    List<FileInfo> mapToFileInfoList(List<FileInfoDto> fileInfoDtoList);

}