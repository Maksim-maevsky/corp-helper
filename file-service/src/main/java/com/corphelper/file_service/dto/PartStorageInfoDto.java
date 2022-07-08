package com.corphelper.file_service.dto;

import com.corphelper.file_service.entity.part.PartInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartStorageInfoDto {

    private Set<String> partStorageNameSet;

    private List<PartInfo> partList;

}
