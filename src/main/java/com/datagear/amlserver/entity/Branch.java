package com.datagear.amlserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int branchId;
    @Column
    private String address;
    @Column
    private String city;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="bank_id")
    private Bank bank;
    @Column
    private LocalDateTime createdAt;
    @Column
    private Boolean isDeleted;
    @Column
    private LocalDateTime deletedAt;
}
