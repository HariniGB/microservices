package com.eazydineapp.menu.service.impl;

import com.eazydineapp.menu.dao.interfaces.RestaurantDAO;
import com.eazydineapp.menu.model.Item;
import com.eazydineapp.menu.model.Restaurant;
import com.eazydineapp.menu.service.interfaces.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    RestaurantDAO restaurantDAO;

    @Override
    public Optional<Restaurant> createRestaurant(Restaurant restaurant) {
        Optional<Restaurant> createdRestaurant = Optional.ofNullable(restaurantDAO.save(restaurant));
        return createdRestaurant;
    }

    @Override
    public List<Restaurant> readAllRestaurants() {
        List <Restaurant> restaurants = restaurantDAO.findAll();
        if (restaurants!=null) {
            return restaurants;
        }
        else{
            return null;
        }
    }

    @Override
    public Optional<Restaurant> readRestaurant(Long restaurantId) {
        return restaurantDAO.findById(restaurantId);
    }

    @Override
    public Optional<Restaurant> updateRestaurant(Long restaurantId, Restaurant restaurantInBound) {

        if(restaurantDAO.existsById(restaurantId)) {
            restaurantInBound.setId(restaurantId);
            return Optional.ofNullable(restaurantDAO.save(restaurantInBound));
        }
        return Optional.ofNullable(null);
    }

    @Override
    public Optional<Restaurant> deleteRestaurant(Long restaurantId) {
        Optional<Restaurant> restaurantToDelete = restaurantDAO.findById(restaurantId);
        if(restaurantToDelete.isPresent()) {
            restaurantDAO.deleteById(restaurantId);
        }
        return restaurantToDelete;
    }
}
