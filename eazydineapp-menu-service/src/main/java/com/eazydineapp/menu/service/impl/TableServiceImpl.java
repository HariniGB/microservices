package com.eazydineapp.menu.service.impl;

import com.eazydineapp.menu.dao.interfaces.TableDAO;
import com.eazydineapp.menu.model.Table;
import com.eazydineapp.menu.service.interfaces.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableDAO tableDAO;

    @Override
    public List<Table> readAllTables() {
        List <Table> tables = tableDAO.findAll();
        if(tables != null)
            return tables;
        else
            return null;
    }

    @Override
    public Optional<Table> readTable(Long tableId) {
        return tableDAO.findById(tableId);
    }

    @Override
    public Optional<Table> createTable(Table table) {
        Optional<Table> createdTable = Optional.ofNullable(tableDAO.save(table));
        return createdTable;
    }

    @Override
    public Optional<Table> updateTable(Long tableId, Table tableInBound) {
        if(tableDAO.existsById(tableId)) {
            tableInBound.setId(tableId);
            return Optional.ofNullable(tableDAO.save(tableInBound));
        }
        return Optional.ofNullable(null);
    }

    @Override
    public Optional<Table> deleteTable(Long tableId) {
        Optional<Table> tableToDelete = tableDAO.findById(tableId);
        if(tableToDelete.isPresent()) {
            tableDAO.deleteById(tableId);
        }
        return tableToDelete;
    }

    @Override
    public  void deleteAllTables() {
        tableDAO.deleteAll();
    }

    @Override
    public List<Table> readAllRestaurantTables(Long restaurantId) {
        List <Table> tables = tableDAO.findTablesByRestaurantIdOrderByNumberAsc(restaurantId);
        if(tables != null)
            return tables;
        else
            return null;
    }

    @Override
    public void deleteAllRestaurantTables(Long restaurantId) {
        List <Table> tables = tableDAO.findTablesByRestaurantIdOrderByNumberAsc(restaurantId);
        if(tables != null)
            tableDAO.deleteAll(tables);
    }
}
