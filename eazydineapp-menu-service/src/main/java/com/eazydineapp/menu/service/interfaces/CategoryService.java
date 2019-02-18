package com.eazydineapp.menu.service.interfaces;


import com.eazydineapp.menu.model.Category;
import com.eazydineapp.menu.model.User;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

	Optional<Category> createCategory(Category category);
    List<Category> readAllCategories();
    List<Category> readAllRestaurantCategories(Long restaurantId);
    Optional<Category> readCategory(Long categoryId);
    Optional<Category> updateCategory(Long categoryId, Category categoryInBound);
    Optional<Category> deleteCategory(Long categoryId);
    void deleteAllCategories();
    void deleteAllRestaurantCategories(Long restaurantId);

}
