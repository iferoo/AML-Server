package com.datagear.amlserver.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "atm", schema = "main")
public class ATM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int atmId;
    @Column
    private String location;
    @Column
    private LocalDateTime createdAt;
    @Column
    private Boolean isDeleted;
    @Column
    private LocalDateTime deletedAt;
}
