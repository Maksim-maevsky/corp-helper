package com.corphelper.mailparser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartInfo {

    private UUID id;

    private int count;

    private short partStorageId;

    private UUID partId;

    private LocalDateTime createDate;

}
