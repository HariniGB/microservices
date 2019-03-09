package com.eazydineapp.menu.dao.interfaces;

import com.eazydineapp.menu.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableDAO extends JpaRepository<Table,Long> {

    Optional<Table> findById(Long tableId);
    List<Table> findTablesByRestaurantIdOrderByNumberAsc(Long restaurantId);
}
