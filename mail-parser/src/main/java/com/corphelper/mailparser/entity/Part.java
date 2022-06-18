package com.corphelper.mailparser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "parts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Part {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private UUID id;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "brand")
    private String brand;

    @Column(name = "count")
    private int count;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "part_storage_id")
    private PartStorage partStorage;

}
