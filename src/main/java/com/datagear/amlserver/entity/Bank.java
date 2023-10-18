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
    @Column(columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdAt;
    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;
    @Column(columnDefinition = "timestamp")
    private LocalDateTime deletedAt;

}
