package com.corphelper.mailparser.dto;

import com.corphelper.mailparser.entity.Part;
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

    private List<Part> partList;

}
