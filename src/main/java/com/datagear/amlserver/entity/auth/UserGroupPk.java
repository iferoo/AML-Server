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
@Embeddable
public class UserGroupPk implements Serializable {
    @Column
    private Long groupId;
    @Column
    private Long userId;
}
