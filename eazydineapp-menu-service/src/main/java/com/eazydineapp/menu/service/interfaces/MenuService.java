package com.eazydineapp.menu.service.interfaces;

import com.eazydineapp.menu.model.Menu;
import com.eazydineapp.menu.model.User;

import java.util.List;
import java.util.Optional;

public interface MenuService {

     Optional<Menu> createMenu(Menu menu);
     List<Menu> readAllRestaurantMenu(Long restaurantId);

    Optional<Menu> readMenu(Long menuId);
    Optional<Menu> updateMenu(Long menuId, Menu menuInBound);
    Optional<Menu> deleteMenu(Long menuId);
    void deleteAll();
    void deleteAllRestaurantMenu(Long restaurantId);

}
