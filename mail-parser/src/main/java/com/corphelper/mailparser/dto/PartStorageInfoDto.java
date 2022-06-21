package com.corphelper.mailparser.dto;

import com.corphelper.mailparser.entity.Part;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartStorageInfoDto {

    private String partStorageName;

    private List<Part> partList;

}
