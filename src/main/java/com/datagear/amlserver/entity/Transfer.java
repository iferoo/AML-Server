package com.datagear.amlserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "transfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
    @ManyToOne
    @JoinColumn(name = "reciever_id")
    private Account reciever;
    @Column
    private String status;
    @Column(columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdAt;
    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;
    @Column
    private LocalDateTime deletedAt;
}
