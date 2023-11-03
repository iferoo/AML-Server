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
@Table(name = "employee", schema = "main")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int employeeId;
    @Column
    private String name;
    @Column
    private Integer salary;
    @Column
    private String email;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @Column
    private LocalDateTime createdAt;
    @Column
    private Boolean isDeleted;
    @Column
    private LocalDateTime deletedAt;
}
