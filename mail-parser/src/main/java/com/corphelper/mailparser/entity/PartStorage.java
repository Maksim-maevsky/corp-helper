package com.corphelper.mailparser.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "part_storages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartStorage {

    @Id
    private short id;

    @Column(name = "name")
    private String name;
}
