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
@Table(name = "customer", schema = "main")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int customerId;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String email;
    @Column
    private LocalDateTime createdAt;
    @Column
    private Boolean isDeleted;
    @Column
    private LocalDateTime deletedAt;

}
