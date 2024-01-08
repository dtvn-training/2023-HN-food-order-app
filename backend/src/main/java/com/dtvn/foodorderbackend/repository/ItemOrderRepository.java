package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOrderRepository extends JpaRepository<ItemOrder,Long> {

}
