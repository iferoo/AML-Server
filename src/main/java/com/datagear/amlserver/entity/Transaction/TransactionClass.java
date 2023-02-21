package com.datagear.amlserver.entity.Transaction;

import com.datagear.amlserver.entity.Account.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TransactionClass {

    private int id;
    private String method;
    private String operation;
    private Integer amount;
    private String date;
    private Integer account;


}
