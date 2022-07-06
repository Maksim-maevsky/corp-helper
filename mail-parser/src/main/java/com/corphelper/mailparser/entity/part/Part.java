package com.corphelper.mailparser.entity.part;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Part {

    private UUID id;

    private String code;

    private String description;

    private int brand;

    private LocalDateTime createDate;

}
