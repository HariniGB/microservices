package com.eazydineapp.menu.service.impl;

import com.eazydineapp.menu.dao.interfaces.RestaurantDAO;
import com.eazydineapp.menu.model.Item;
import com.eazydineapp.menu.model.Restaurant;
import com.eazydineapp.menu.service.interfaces.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    RestaurantDAO restaurantDAO;


    private final Logger log = LoggerFactory.getLogger(this.getClass());


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
    public List<Restaurant> readAllUserRestaurants(String uuid) {
        List <Restaurant> restaurants = restaurantDAO.findAllByUuidOrderByName(uuid);
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
    public Optional<Restaurant> readUIDRestaurant(String firebaseId) {
        Optional<Restaurant> restaurant = restaurantDAO.findRestaurantByFirebaseId(firebaseId);
        return restaurant;
    }

    @Override
    public Optional<Restaurant> updateRestaurant(Long restaurantId, Restaurant restaurantInBound) {
        Optional<Restaurant> optionalStoredRestaurant = restaurantDAO.findById(restaurantId);
        if(optionalStoredRestaurant.isPresent()) {
            Restaurant storedRestaurant = optionalStoredRestaurant.get();

            restaurantInBound.setId(restaurantId);
            restaurantInBound.setMenus(storedRestaurant.getMenus());
            restaurantInBound.setCategories(storedRestaurant.getCategories());
            restaurantInBound.setTables(storedRestaurant.getTables());
            log.info("RestaurantDTO UUID :: " +restaurantInBound.getUuid() );
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
