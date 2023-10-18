package com.datagear.amlserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int bankId;
    @Column
    private String name;
    @Column
    private LocalDateTime createdAt;
    @Column
    private Boolean isDeleted;
    @Column
    private LocalDateTime deletedAt;

}
