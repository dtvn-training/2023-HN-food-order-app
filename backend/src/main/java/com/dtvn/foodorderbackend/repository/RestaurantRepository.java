package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAllBySelectedTrue();

    boolean existsByUrlAndDeletedFalse(String url);

    Restaurant findByDeliveryIdAndDeleted(long deliveryId, boolean deleted);

    Restaurant findByUrlAndDeletedTrue(String url);

    @Modifying
    @Transactional
    @Query("update Restaurant  r set r.selected = :selected where r.deleted = false and r.deliveryId = :deliveryId")
    void setRestaurantSelected(long deliveryId, boolean selected);

    Restaurant findByDeliveryIdAndSelected(long deliveryId, boolean selected);

    @Modifying
    @Transactional
    @Query("update Restaurant  r set r.deleted = :deleted where r.deliveryId = :deliveryId")
    void setRestaurantDeleted(long deliveryId, boolean deleted);
}
