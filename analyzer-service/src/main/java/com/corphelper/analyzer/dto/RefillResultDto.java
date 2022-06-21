package com.corphelper.analyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefillResultDto {

    private String targetPartStorageName;

    private String currentPartStorageName;

    private List<PartDto> resultPartDtoList;

}
