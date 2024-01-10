package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserCartRepository extends JpaRepository<UserCart, Long> {
    boolean existsByCreatedByIdAndId(long userId, long userCartId);


    @Transactional
    @Modifying
    @Query("update UserCart set quantity = :quantity where createdById = :userId and dishId = :dishId")
    void changeQuantityByUserIdAndDishId(long userId, long dishId, int quantity);

    @Transactional
    @Modifying
    void deleteByCreatedByIdAndId(long userId,long userCartId);


    boolean existsByCreatedByIdAndDishId(long userId, long dishId);
}
