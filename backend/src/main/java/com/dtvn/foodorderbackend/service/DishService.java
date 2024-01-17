package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.model.dto.request.DishUpdate;
import com.dtvn.foodorderbackend.model.dto.response.DishRes;
import com.dtvn.foodorderbackend.model.entity.Dish;
import com.dtvn.foodorderbackend.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<DishRes> getDishes() {
        List<Dish> dishes = dishRepository.findAllByActiveTrueAndApprovedTrue();
        List<DishRes> response = new ArrayList<>();
        for(Dish item : dishes){
            response.add(DishRes.builder()
                    .id(item.getId())
                    .price(item.getPrice())
                    .image(item.getImage())
                    .name(item.getName())
                    .restaurantName(item.getCategory().getRestaurant().getName())
                    .build());
        }
        return response;
    }

    public boolean update(DishUpdate request) {
        Dish dish = dishRepository.findById(request.getId()).orElse(null);
        if (dish == null)return false;
        if(request.getActive() != null)dish.setActive(request.getActive());
        if(request.getApproved() != null)dish.setApproved(request.getApproved());
        dishRepository.save(dish);
        return true;
    }
}
