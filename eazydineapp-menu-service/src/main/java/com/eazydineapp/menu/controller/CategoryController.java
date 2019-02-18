package com.eazydineapp.menu.controller;

import com.eazydineapp.menu.model.Category;
import com.eazydineapp.menu.service.interfaces.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());


	@ApiOperation(value = "Create Category", notes="Create a Category",nickname = "createCategory")
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createCategory(@RequestBody Category category) {

		Optional<Category> savedCategory = categoryService.createCategory(category);
		if (savedCategory.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{categoryId}")
					.buildAndExpand(savedCategory.get().getId()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}

	@ApiOperation(value = "Read Category", notes="Read a Category with categoryId",nickname = "readCategory")
	@RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
	public ResponseEntity<?> readCategory(@PathVariable("categoryId") Long categoryId) {
		Optional<Category> category = categoryService.readCategory(categoryId);
		return new ResponseEntity<Optional<Category>>(category, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete Category", notes="Delete a category with an categoryId",nickname = "deleteCategory")
	@RequestMapping(value = "/{categoryId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Long categoryId) {
		Optional<Category> category = categoryService.deleteCategory(categoryId);
		if(category.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Update Category", notes="Update Category with a categoryId",nickname = "updateCategory")
	@RequestMapping(value = "/{categoryId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody Category category) {
		Optional<Category> updateCategory = categoryService.updateCategory(categoryId,category);
		if(updateCategory.isPresent()) {
			return new ResponseEntity<Optional<Category>>(updateCategory, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "ReadAll Category of a Restaurant", notes="Get All the Categories for given restaurant, input restaurant required",nickname = "getRestaurantCategories")
	@RequestMapping(value = "/", params="restaurantId" ,method = RequestMethod.GET)
	public ResponseEntity<?> getRestaurantCategories(@RequestParam("restaurantId") Long restaurantId) {
		List<Category> restaurantCategories =  categoryService.readAllRestaurantCategories(restaurantId);
		if(restaurantCategories!=null) {
			return new ResponseEntity<List<Category>>(restaurantCategories, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
