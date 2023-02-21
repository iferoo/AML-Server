package com.datagear.amlserver.entity.Transfer;

import com.datagear.amlserver.entity.Account.Account;
import com.datagear.amlserver.entity.Transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class TransferClass {
    private int id;
    private Integer transaction;
    private Integer reciever;
    private String status;
}
