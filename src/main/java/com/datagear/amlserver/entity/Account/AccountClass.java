package com.datagear.amlserver.entity.Account;

import com.datagear.amlserver.entity.Branch.Branch;
import com.datagear.amlserver.entity.Customer;
import com.datagear.amlserver.entity.Employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AccountClass {
    private int id;
    private Integer balance;
    private String type;
    private Integer employee;
    private Integer branch;
    private Integer customer;
}
