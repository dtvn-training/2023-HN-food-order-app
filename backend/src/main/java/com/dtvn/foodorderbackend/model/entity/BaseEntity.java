package com.dtvn.foodorderbackend.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.apache.poi.ss.formula.functions.T;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.sql.Timestamp;

@MappedSuperclass
@Getter
public abstract class BaseEntity {
    @JoinColumn(name = "create_by", updatable = false, insertable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    User createdBy;

    @Column(name = "create_by")
    Long createdById;

    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    Timestamp createdTime;

    @JoinColumn(name = "update_by", updatable = false, insertable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    User updatedBy;

    @Column(name = "update_by")
    Long updatedById;

    @UpdateTimestamp
    @Column(name = "update_time")
    Timestamp lastUpdateTime;

    public void update(long userId) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (createdTime == null) {
            createdTime = now;
        }
        if (createdById == null) {
            createdById = userId;
        }
        lastUpdateTime = now;
        updatedById = userId;
    }
}
