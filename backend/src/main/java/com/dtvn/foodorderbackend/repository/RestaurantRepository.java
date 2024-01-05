package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAllBySelectedTrue();

    boolean existsByDeliveryIdAndDeletedFalse(long deliveryId);

    @Modifying
    @Transactional
    @Query("update Restaurant set selected = true where deleted = false and deliveryId = :deliveryId")
    void setRestaurantSelected(long deliveryId);
}
