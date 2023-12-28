package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.mapper.BaseMapper;
import com.dtvn.foodorderbackend.model.response.SimpleRestaurantResponse;
import com.dtvn.foodorderbackend.model.entity.Restaurant;
import com.dtvn.foodorderbackend.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    final RestaurantRepository restaurantRepository;
    final BaseMapper mapper;
    Logger logger = LoggerFactory.getLogger(RestaurantService.class);
    public List<SimpleRestaurantResponse> getAllPresentRestaurant() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return mapper.mapList(restaurants, SimpleRestaurantResponse.class);
    }
    public List<SimpleRestaurantResponse> getRestaurantSelected(){
        List<Restaurant> restaurantsSelected = restaurantRepository.findAllBySelectedTrue();
        return mapper.mapList(restaurantsSelected, SimpleRestaurantResponse.class);
    }

    public Restaurant getRestaurantById(long deliveryId){
        return restaurantRepository.findById(deliveryId).orElse(null);
    }
}
