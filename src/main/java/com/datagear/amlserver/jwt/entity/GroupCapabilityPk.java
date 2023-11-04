package com.datagear.amlserver.jwt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class GroupCapabilityPk implements Serializable {
    @Column
    private Long groupId;
    @Column
    private Long capabilityId;
}
