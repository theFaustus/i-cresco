package com.evil.inc.icresco.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity {
    @Id
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "UUID")
    protected String id;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(final LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getId() {
        return id;
    }

    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof AbstractEntity)) {
            return false;
        }
        AbstractEntity otherEntity = (AbstractEntity) other;
        return Objects.equals(getId(), otherEntity.getId());
    }

    public int hashCode() {
        return getClass().hashCode();
    }
}