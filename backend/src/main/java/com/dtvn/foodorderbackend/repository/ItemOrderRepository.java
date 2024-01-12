package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemOrderRepository extends JpaRepository<ItemOrder,Long> {
    List<ItemOrder> findAllByApprovedFalseAndDeletedFalse();

    ItemOrder findByIdAndApprovedFalseAndDeletedFalse(long itemOrderId);

}