package com.eazydineapp.menu.dao.interfaces;


import com.eazydineapp.menu.model.Category;
import com.eazydineapp.menu.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantDAO extends JpaRepository<Restaurant,Long> {
    Optional<Restaurant> findById(Long restaurantId);
}
