package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    Dish findByActiveTrueAndApprovedFalseAndId(long id);
    Dish findByActiveTrueAndApprovedTrueAndId(long id);
    List<Dish> findAllByActiveTrueAndApprovedTrue();

    List<Dish> findByActiveTrue();
}
