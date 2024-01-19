package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findAllByCreatedById(long createdById);
}
