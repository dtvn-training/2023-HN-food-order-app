package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.model.dto.request.DishUpdateActive;
import com.dtvn.foodorderbackend.model.dto.response.DishRes;
import com.dtvn.foodorderbackend.model.entity.Dish;
import com.dtvn.foodorderbackend.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;


    public List<DishRes> getDishes() {
        List<Dish> dishes = dishRepository.findAll();
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

    public boolean updateActive(DishUpdateActive request) {
        Dish dish = dishRepository.findById(request.getId()).orElse(null);
        if (dish == null)return false;
        dish.setActive(request.getActive());
        dishRepository.save(dish);
        return true;
    }
}
