package com.herokusmallapp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
@Data
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column
    private String name;
}
