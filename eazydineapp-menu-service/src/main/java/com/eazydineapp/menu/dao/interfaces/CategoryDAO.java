package com.eazydineapp.menu.dao.interfaces;


import com.eazydineapp.menu.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryDAO extends JpaRepository<Category,Long> {
    Optional<Category> findById(Long categoryId);
    List<Category> findCategoriesByRestaurant_Id(Long restaurantId);
}
