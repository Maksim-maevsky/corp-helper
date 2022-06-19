package com.corphelper.mailparser.entity;

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

    private LocalDateTime createDate;

    private String brand;

    private int count;

    private short partStorageId;

}
