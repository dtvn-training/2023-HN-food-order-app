package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.Bill;
import org.hibernate.validator.internal.engine.resolver.JPATraversableResolver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Long> {
}
