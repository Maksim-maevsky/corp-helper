package com.corphelper.analyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefillRequestDto implements Serializable {

    private String targetPartStorageName;

    private String currentPartStorageName;

}
