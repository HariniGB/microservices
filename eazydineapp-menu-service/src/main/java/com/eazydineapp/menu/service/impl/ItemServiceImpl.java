package com.eazydineapp.menu.service.impl;

import com.eazydineapp.menu.dao.interfaces.ItemDAO;
import com.eazydineapp.menu.model.Item;
import com.eazydineapp.menu.service.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    ItemDAO itemDAO;

    @Override
    public Optional<Item> createItem(Item item) {
        Optional<Item> createdItem = Optional.ofNullable(itemDAO.save(item));
        return createdItem;
    }

    @Override
    public List<Item> readAllMenuItems(Long menuId) {
        List <Item> items = itemDAO.findAllByMenu_IdOrderByCategory_RankAscPriceAsc(menuId);
        if (items!=null) {
            return items;
        }
        else{
            return null;
        }
    }

    @Override
    public Optional<Item> readItem(Long itemId) {
        return itemDAO.findById(itemId);
    }

    @Override
    public Optional<Item> updateItem(Long itemId, Item itemInBound) {
        if(itemDAO.existsById(itemId)) {
            itemInBound.setId(itemId);
            return Optional.ofNullable(itemDAO.save(itemInBound));
        }
        return Optional.ofNullable(null);
    }

    @Override
    public Optional<Item> deleteItem(Long itemId) {
        Optional<Item> itemToDelete = itemDAO.findById(itemId);
        if(itemToDelete.isPresent()) {
            itemDAO.deleteById(itemId);
        }
        return itemToDelete;
    }

    @Override
    public void deleteAllItems() {

    }

    @Override
    public void deleteAllMenuItems(Long menuId) {
        List<Item> items = itemDAO.findAllByMenu_IdOrderByCategoryAscNameAsc(menuId);
        if (items != null){
            itemDAO.deleteAll(items);
        }
    }
}
