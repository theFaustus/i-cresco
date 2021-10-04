package com.evil.inc.icresco.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Comparator;

@Entity
@Table(name = "user_roles", indexes = @Index(name = "unique_user_roles", columnList = "id, role_name"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserRole extends AbstractEntity{

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;
}
