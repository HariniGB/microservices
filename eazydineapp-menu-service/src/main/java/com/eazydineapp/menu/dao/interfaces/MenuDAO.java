package com.eazydineapp.menu.dao.interfaces;

import com.eazydineapp.menu.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuDAO extends JpaRepository<Menu,Long>{

    List<Menu> findAllByRestaurant_IdOrderByIdDesc(Long restaurantId);
}
