package com.datagear.amlserver.entity.Transfer;

import com.datagear.amlserver.entity.Account.Account;
import com.datagear.amlserver.entity.Transaction.Transaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "transfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne()
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
    @ManyToOne()
    @JoinColumn(name = "reciever_id")
    private Account reciever;

    @Column(name = "status")
    private String status;

}
