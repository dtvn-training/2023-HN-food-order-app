package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.model.entity.Dish;
import com.dtvn.foodorderbackend.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {
    final DishRepository dishRepository;

    public boolean approveDish(long dishId) {
        Dish dish = dishRepository.findByActiveTrueAndApprovedFalseAndId(dishId);
        if (dish == null) {
            return false;
        }
        dish.setApproved(true);
        dishRepository.save(dish);
        return true;
    }

    public List<Dish> getDishApproved() {
        return dishRepository.findAllByActiveTrueAndApprovedTrue();
    }

    public boolean disApproveDish(long dishId) {
        Dish dish = dishRepository.findByActiveTrueAndApprovedTrueAndId(dishId);
        if (dish == null) {
            return false;
        }
        dish.setApproved(false);
        dishRepository.save(dish);
        return true;
    }

}
