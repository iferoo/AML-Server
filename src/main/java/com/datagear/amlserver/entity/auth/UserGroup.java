package com.datagear.amlserver.entity.auth;

import jakarta.persistence.*;
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
@Table(name = "_usergroup", schema = "security")
public class UserGroup implements Serializable {
    @EmbeddedId
    private UserGroupPk userGroupId;
}
