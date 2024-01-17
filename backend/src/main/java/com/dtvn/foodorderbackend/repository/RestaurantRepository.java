package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAllByDeletedFalse();
    List<Restaurant> findAllBySelectedTrueAndDeletedFalse();

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

    @Query("""
           SELECT r
           FROM Restaurant r
           where
           (:name is null or r.name like %:name%) and
           (:selected is null or r.selected = :selected) and
           (r.deleted = false)
           """)
    List<Restaurant> getByCategory(String name, Boolean selected);

    @Query(value = """
            select * from restaurant where
            (:name is null or name like %:name%) and
            (selected = true) and
            (deleted = false)
            """, nativeQuery = true)
    List<Restaurant> findRestaurantByName(String name);
}
