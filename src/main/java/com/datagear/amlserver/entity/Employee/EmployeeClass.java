package com.datagear.amlserver.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class EmployeeClass {
    private int id;
    private String name;
    private Integer salary;
    private String email;
    private Integer branch;
}
