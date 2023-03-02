package com.datagear.amlserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String email;
    @Column(columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdAt;
    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;
    @Column(columnDefinition = "timestamp")
    private LocalDateTime deletedAt;

}
