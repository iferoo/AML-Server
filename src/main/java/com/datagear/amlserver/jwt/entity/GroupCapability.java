package com.datagear.amlserver.jwt.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_groupcapability", schema = "security")
public class GroupCapability implements Serializable {
    @EmbeddedId
    private GroupCapabilityPk groupCapabilityPk;
}
