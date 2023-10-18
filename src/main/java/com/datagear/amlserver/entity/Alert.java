package com.datagear.amlserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "alert")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int alertId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column
    private int transactions;

    @Column
    private String risk;

    @Column
    private LocalDate createdAt;

}
