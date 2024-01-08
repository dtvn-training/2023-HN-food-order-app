package com.dtvn.foodorderbackend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.apache.poi.ss.formula.functions.T;

import java.sql.Timestamp;

@MappedSuperclass
public class BaseEntity {
    @Column(name = "create_by", updatable = false)
    Long userIdCreated;
    @Column(name = "create_time", updatable = false)
    Timestamp createdTime;

    @Column(name = "update_by")
    Long lastUpdateBy;

    @Column(name = "update_time")
    Timestamp lastUpdateTime;

    public void update(long userId) {
        if (createdTime == null) {
            createdTime = new Timestamp(System.currentTimeMillis());
        }
        if (userIdCreated == null) {
            userIdCreated = userId;
        }
        lastUpdateTime = new Timestamp(System.currentTimeMillis());
        lastUpdateBy = userId;
    }
}
