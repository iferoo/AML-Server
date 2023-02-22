package com.datagear.amlserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "method")
    private String method;
    @Column(name = "operation")
    private String operation;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "date")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
