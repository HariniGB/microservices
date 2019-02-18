package com.eazydineapp.menu.service.impl;

import com.eazydineapp.menu.dao.interfaces.MenuDAO;
import com.eazydineapp.menu.model.Category;
import com.eazydineapp.menu.model.Menu;
import com.eazydineapp.menu.service.interfaces.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDAO menuDAO;

    @Override
    public Optional<Menu> createMenu(Menu menu) {
        Optional<Menu> createdMenu = Optional.ofNullable(menuDAO.save(menu));
        return createdMenu;
    }

    @Override
    public List<Menu> readAllRestaurantMenu(Long restaurantId) {
        List <Menu> menus = menuDAO.findAllByRestaurant_IdOrderByIdDesc(restaurantId);
        if(menus != null)
            return menus;
        else
            return null;
    }

    @Override
    public Optional<Menu> readMenu(Long menuId) { return menuDAO.findById(menuId); }



    @Override
    public Optional<Menu> updateMenu(Long menuId, Menu menuInBound) {
        if (menuDAO.existsById(menuId)) {
            menuInBound.setId(menuId);
            return Optional.ofNullable(menuDAO.save(menuInBound));
        }
        return Optional.ofNullable(null);
    }

    @Override
    public Optional<Menu> deleteMenu(Long menuId) {
        Optional<Menu> menuToDelete = menuDAO.findById(menuId);
        if(menuToDelete.isPresent()) {
            menuDAO.deleteById(menuId);
        }
        return menuToDelete;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteAllRestaurantMenu(Long restaurantId) {
        List <Menu> menus = menuDAO.findAllByRestaurant_IdOrderByIdDesc(restaurantId);
        if(menus != null)
            menuDAO.deleteAll(menus);
    }
}
