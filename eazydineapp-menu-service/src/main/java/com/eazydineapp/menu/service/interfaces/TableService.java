package com.eazydineapp.menu.service.interfaces;

import com.eazydineapp.menu.model.Table;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface TableService {

    Optional<Table> createTable(Table table);
    List<Table> readAllTables();
    List<Table> readAllRestaurantTables(Long restaurantId);
    Optional<Table> readTable(Long tableId);
    Optional<Table> updateTable(Long tableId, Table tableInBound);
    Optional<Table> deleteTable(Long tableId);
    void deleteAllTables();
    void deleteAllRestaurantTables(Long restaurantId);
}
