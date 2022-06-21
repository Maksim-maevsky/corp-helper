package com.corphelper.analyzer.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartStorageInfoDto {

    private String partStorageName;

    private List<PartDto> partList;

}
