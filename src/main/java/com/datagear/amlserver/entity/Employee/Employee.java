package com.datagear.amlserver.entity.Employee;

import com.datagear.amlserver.entity.Branch.Branch;
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
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "salary")
    private Integer salary;
    @Column(name = "email")
    private String email;
    @ManyToOne()
    @JoinColumn(name = "branch_id")
    private Branch branch;
}
