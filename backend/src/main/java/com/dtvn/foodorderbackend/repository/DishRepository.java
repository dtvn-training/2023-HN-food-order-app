package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {

    Dish findByActiveTrueAndApprovedFalseAndId(long id);
    Dish findByActiveTrueAndApprovedTrueAndId(long id);
    List<Dish> findAllByActiveTrueAndApprovedTrue();
}
