package com.corphelper.analyzer.dto;


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

    private List<PartInfoDto> partList;

}
