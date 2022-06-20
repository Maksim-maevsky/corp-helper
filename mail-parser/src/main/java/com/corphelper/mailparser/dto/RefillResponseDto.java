package com.corphelper.mailparser.dto;

import com.corphelper.mailparser.entity.Part;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefillResponseDto {

    private List<Part> targetPartStorageList;

    private List<Part> currentPartStorageList;

}
