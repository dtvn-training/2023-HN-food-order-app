package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.mapper.Mapper;
import com.dtvn.foodorderbackend.model.entity.Restaurant;
import com.dtvn.foodorderbackend.model.response.SimpleRestaurantResponse;
import com.dtvn.foodorderbackend.repository.RestaurantRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    final RestaurantRepository restaurantRepository;
    final Mapper mapper;
    final HttpServletResponse response;
    final ShopeeFoodService shopeeFoodService;
    Logger logger = LoggerFactory.getLogger(RestaurantService.class);

    public List<SimpleRestaurantResponse> getAllPresentRestaurant() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return mapper.mapList(restaurants, SimpleRestaurantResponse.class);
    }

    public void fetchData(String url){}

    public void fetchData(long deliveryId){}

    public void fetchData(Restaurant restaurant){}

    public List<SimpleRestaurantResponse> getRestaurantSelected() {
        List<Restaurant> restaurantsSelected = restaurantRepository.findAllBySelectedTrue();
        return mapper.mapList(restaurantsSelected, SimpleRestaurantResponse.class);
    }

    public Restaurant getRestaurantById(long deliveryId) {
        return restaurantRepository.findById(deliveryId).orElse(null);
    }

    public boolean approveRestaurant(long deliveryId) throws Exception{
        if (restaurantRepository.existsByDeliveryIdAndDeletedFalse(deliveryId)) {
            return false;
        }
        fetchData(shopeeFoodService.getRestaurantDishes(deliveryId));
        // TODO: some case for fetching data
        return true;
    }
}
