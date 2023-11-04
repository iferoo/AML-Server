package com.datagear.amlserver.jwt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_capability", schema = "security")
public class Capability {
    @Id
    private Long capabilityId;
    @Column
    private String name;
    @Column
    private String description;
}
