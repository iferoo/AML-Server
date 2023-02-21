package com.datagear.amlserver.entity.Branch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class BranchClass {
    private int id;
    private String address;
    private String city;
    private Integer bank;
}
