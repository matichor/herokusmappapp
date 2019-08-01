package com.herokusmallapp.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "news")
@Getter
@Setter
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="subject")
    private String subject;

    @Column(name="contents")
    private String contents;

    @Column(name = "creator_name")
    private String creatorName;

    @Type(type="org.hibernate.type.BinaryType")
    @Column(name = "teammate")
    private byte[] teammatePicture;

    @Column(name = "created_at")
    Date created;

    @PrePersist
    public void onCreate() {
    created = new Date(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()
                .toEpochMilli());
    }

}
