package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.Dish;
import com.dtvn.foodorderbackend.model.entity.DishCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface DishRepository extends JpaRepository<Dish,Long>{
    @Transactional
    long deleteDishByCategory(DishCategory dishCategory);

}
