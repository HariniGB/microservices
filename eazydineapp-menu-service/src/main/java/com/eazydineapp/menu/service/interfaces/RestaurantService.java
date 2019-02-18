package com.eazydineapp.menu.service.interfaces;


import com.eazydineapp.menu.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
	Optional<Restaurant> createRestaurant(Restaurant restaurant);
    List<Restaurant> readAllRestaurants();
    Optional<Restaurant> readRestaurant(Long restaurantId);
    Optional<Restaurant> updateRestaurant(Long restaurantId, Restaurant restaurantInBound);
    Optional<Restaurant> deleteRestaurant(Long restaurantId);
}
