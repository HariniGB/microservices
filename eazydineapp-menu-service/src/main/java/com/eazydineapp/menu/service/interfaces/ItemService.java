package com.eazydineapp.menu.service.interfaces;

import com.eazydineapp.menu.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

     Optional<Item> createItem(Item item);
     List<Item> readAllMenuItems(Long menuId);
     Optional<Item> readItem(Long itemId);
     Optional<Item> updateItem(Long itemId, Item itemInBound);
     Optional<Item> deleteItem(Long itemId);
     void deleteAllItems();
     void deleteAllMenuItems(Long menuId);

}
