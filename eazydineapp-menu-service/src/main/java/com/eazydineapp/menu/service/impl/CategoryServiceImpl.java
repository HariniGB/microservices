package com.eazydineapp.menu.service.impl;

import java.util.List;
import java.util.Optional;

import com.eazydineapp.menu.dao.interfaces.CategoryDAO;
import com.eazydineapp.menu.model.Category;
import com.eazydineapp.menu.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CategoryServiceImpl implements CategoryService{


	@Autowired
	private CategoryDAO categoryDAO;

	@Override
	public List<Category> readAllCategories() {
		List <Category> categories = categoryDAO.findAll();
		if(categories != null)
			return categories;
		else
			return null;
	}

	@Override
	public Optional<Category> readCategory(Long categoryId) {
        return categoryDAO.findById(categoryId);
	}

	@Override
	public Optional<Category> createCategory(Category category) {
		Optional<Category> createdCategory = Optional.ofNullable(categoryDAO.save(category));
		return createdCategory;
	}

	@Override
	public Optional<Category> updateCategory(Long categoryId, Category categoryInBound) {
		if(categoryDAO.existsById(categoryId)) {
            categoryInBound.setId(categoryId);
            return Optional.ofNullable(categoryDAO.save(categoryInBound));
		}
		return Optional.ofNullable(null);
	}

    @Override
    public Optional<Category> deleteCategory(Long categoryId) {
        Optional<Category> categoryToDelete = categoryDAO.findById(categoryId);
        if(categoryToDelete.isPresent()) {
            categoryDAO.deleteById(categoryId);
        }
        return categoryToDelete;
    }

    @Override
    public  void deleteAllCategories() {
        categoryDAO.deleteAll();
    }

    @Override
    public List<Category> readAllRestaurantCategories(Long restaurantId) {
        List <Category> categories = categoryDAO.findCategoriesByRestaurant_Id(restaurantId);
        if(categories != null)
            return categories;
        else
            return null;
    }

    @Override
    public void deleteAllRestaurantCategories(Long restaurantId) {
        List <Category> categories = categoryDAO.findCategoriesByRestaurant_Id(restaurantId);
        if(categories != null)
            categoryDAO.deleteAll(categories);
    }
}
