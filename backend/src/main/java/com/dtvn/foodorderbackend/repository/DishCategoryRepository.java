package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.DishCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishCategoryRepository  extends JpaRepository<DishCategory,Long> {

}
