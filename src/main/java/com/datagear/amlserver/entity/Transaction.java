package com.datagear.amlserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int transactionId;
    @Column
    private String method;
    @Column
    private String operation;
    @Column
    private Integer amount;
    @Column
    private LocalDate createdDate;
    @Column
    private LocalTime createdTime;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
