package com.herokusmallapp.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "runners")
@Getter
@Setter
public class Runner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private String location;

    private String bio;

    @Type(type="org.hibernate.type.BinaryType")
    @Column(name = "teammate")
    private byte[] teammatePicture;

    private String five;

    private String ten;

    private String half;

    private String marathon;



}
