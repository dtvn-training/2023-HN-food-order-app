package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish,Long>{

}
