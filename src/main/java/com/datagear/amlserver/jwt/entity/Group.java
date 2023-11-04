package com.datagear.amlserver.jwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_group", schema = "security")
public class Group {
    @Id
    private Long groupId;
    @Column
    private String name;
    @ManyToMany
    @JoinTable(
            name = "_groupcapability",
            schema = "security",
            joinColumns = {@JoinColumn(name = "groupId", referencedColumnName = "groupId")},
            inverseJoinColumns = {@JoinColumn(name = "capabilityId", referencedColumnName = "capabilityId")}
    )
    private Set<Capability> capabilities;
}
