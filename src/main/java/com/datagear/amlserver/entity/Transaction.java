package com.datagear.amlserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
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
    @Column
    private int id;
    @Column
    private String method;
    @Column
    private String operation;
    @Column
    private Integer amount;
    @Column
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdAt;
    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;
    @Column
    private LocalDateTime deletedAt;
}
