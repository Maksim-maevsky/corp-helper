package com.corphelper.analyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefillRequestDto {

    private String targetPartStorageName;

    private String currentPartStorageName;

}
