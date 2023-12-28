package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAllBySelectedTrue();
}
