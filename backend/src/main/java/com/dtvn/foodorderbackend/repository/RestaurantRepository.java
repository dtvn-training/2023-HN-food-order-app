package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
