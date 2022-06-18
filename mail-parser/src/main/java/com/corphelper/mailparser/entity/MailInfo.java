package com.corphelper.mailparser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "mail_infos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private UUID id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "from_full_name")
    private String from;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy="mailInfo")
    private List<FileInfo> fileInfoList;

    @Column(name = "date")
    private LocalDateTime dateTime;

}
