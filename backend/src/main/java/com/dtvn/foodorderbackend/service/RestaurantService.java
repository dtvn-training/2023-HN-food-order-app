package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.mapper.Mapper;
import com.dtvn.foodorderbackend.model.dto.response.SimpleRestaurantResponse;
import com.dtvn.foodorderbackend.model.entity.Dish;
import com.dtvn.foodorderbackend.model.entity.DishCategory;
import com.dtvn.foodorderbackend.model.entity.PresentVote;
import com.dtvn.foodorderbackend.model.entity.Restaurant;
import com.dtvn.foodorderbackend.repository.PresentVoteRepository;
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
    final PresentVoteRepository presentVoteRepository;
    final Logger logger = LoggerFactory.getLogger(RestaurantService.class);

    public List<SimpleRestaurantResponse> getAllRestaurantInDatabase() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return mapper.mapList(restaurants, SimpleRestaurantResponse.class);
    }

    public void fetchData(String url) throws Exception {
        fetchData(restaurantRepository.findByUrlAndDeletedTrue(url));

    }

    public void fetchData(Restaurant oldRestaurant) throws Exception {
        Restaurant newRestaurant = shopeeFoodService.getRestaurantDishes(oldRestaurant.getDeliveryId());
        oldRestaurant = mergeRestaurant(oldRestaurant, newRestaurant);
        restaurantRepository.save(oldRestaurant);
    }

    public List<SimpleRestaurantResponse> getRestaurantSelected() {
        List<Restaurant> restaurantsSelected = restaurantRepository.findAllBySelectedTrue();
        return mapper.mapList(restaurantsSelected, SimpleRestaurantResponse.class);
    }

    public Restaurant getRestaurantById(long deliveryId) {
        return restaurantRepository.findById(deliveryId).orElse(null);
    }


    public boolean addRestaurantFromVotePresentToDatabase(long presentVoteId) throws Exception {

        // check restaurant in vote_present
        PresentVote presentVote = presentVoteRepository.findByActiveTrueAndId(presentVoteId);
        if (presentVote == null) {
            return false;
        }
        logger.debug("exists present vote");
        String url = presentVote.getRestaurantUrl();

        // check if restaurant exist in database
        if (restaurantRepository.existsByUrlAndDeletedFalse(url)) {
            return false;
        }
        logger.debug("not exist restaurant in database: ok");

        // check if restaurant was deleted, this case, the return will true, change deleted false and fetch data again
        // if not exist -> crawl data
        // if deleted-> fetch data
        Restaurant restaurant = restaurantRepository.findByUrlAndDeletedTrue(url);
        if (restaurant != null) {
            restaurant.setDeleted(false);
            fetchData(restaurant);
            restaurantRepository.save(restaurant);
            restaurant.setSelected(false);
        } else {
            // this restaurant do not exist in database, fetching is redundant
            restaurant = shopeeFoodService.getRestaurantDishes(shopeeFoodService.getDeliveryId(url));
            restaurantRepository.save(restaurant);
            presentVoteRepository.setActiveFalse(presentVoteId);
        }
        // fetch data
        return true;
    }

    public boolean addRestaurantFromDatabaseToUserList(long deliveryId) {
        Restaurant restaurant = restaurantRepository.findByDeliveryIdAndSelected(deliveryId, false);
        if (restaurant == null) {
            return false;
        }
        // TODO: fetch data if needed
        restaurantRepository.setRestaurantSelected(deliveryId, true);
        return true;
    }


    public boolean removeRestaurantFromUserList(long deliveryId) {
        Restaurant restaurant = restaurantRepository.findByDeliveryIdAndSelected(deliveryId, true);
        if (restaurant == null) {
            return false;
        }
        restaurantRepository.setRestaurantSelected(deliveryId, false);
        return true;
    }

    public boolean setRestaurantDeleted(long deliveryId) {
        Restaurant restaurant = restaurantRepository.findByDeliveryIdAndDeleted(deliveryId, false);
        if (restaurant == null) {
            return false;
        }
        restaurantRepository.setRestaurantDeleted(deliveryId, true);
        return true;
    }

    public Restaurant mergeRestaurant(Restaurant existedRestaurant, Restaurant newRestaurant) {
        for (DishCategory oldCategory : existedRestaurant.getDishCategoryList()) {
            List<DishCategory> newCategories = newRestaurant.getDishCategoryList();
            boolean present = false;
            for (int i = 0; i < newCategories.size(); i++) {
                if (newCategories.get(i).getId() == oldCategory.getId()) {
                    present = true;
                    newCategories.set(i, mergeDishCategory(oldCategory, newCategories.get(i)));
                    break;
                }
            }
            if (!present) {
                oldCategory.deActive();
                newCategories.add(oldCategory);
            }
        }
        existedRestaurant.setDishCategoryList(newRestaurant.getDishCategoryList());
        return existedRestaurant;
    }

    private DishCategory mergeDishCategory(DishCategory existedDishCategory, DishCategory newDishCategory) {
        for (Dish dish : existedDishCategory.getDishList()) {
            List<Dish> newDishes = newDishCategory.getDishList();
            boolean present = false;
            for (Dish newDish : newDishes) {
                if (newDish.getId().equals(dish.getId())) {
                    present = true;
                    break;
                }
            }

            if (!present) {
                dish.deActive();
                newDishes.add(dish);
            }
        }
        existedDishCategory.setDishList(newDishCategory.getDishList());
        return existedDishCategory;
    }
}
