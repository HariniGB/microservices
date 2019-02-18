package com.eazydineapp.menu.controller;

import com.eazydineapp.menu.constants.ApiPathConstants;
import com.eazydineapp.menu.model.Restaurant;
import com.eazydineapp.menu.service.interfaces.RestaurantService;
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
import java.util.Optional;

@RestController
@RequestMapping(ApiPathConstants.RESTAURANT_RESOURCE)
@CrossOrigin(origins = {"*"})
public class RestaurantController {

	@Autowired
	RestaurantService restaurantService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());


	@ApiOperation(value = "Create Restaurant", notes="Create a Restaurant",nickname = "createRestaurant")
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createRestaurant(@RequestBody Restaurant restaurant) {

		Optional<Restaurant> savedRestaurant = restaurantService.createRestaurant(restaurant);
		if (savedRestaurant.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{restaurantId}")
					.buildAndExpand(savedRestaurant.get().getId()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}

	@ApiOperation(value = "Read Restaurant", notes="Read a Restaurant with restaurantId",nickname = "readRestaurant")
	@RequestMapping(value = "/{restaurantId}", method = RequestMethod.GET)
	public ResponseEntity<?> readRestaurant(@PathVariable("restaurantId") Long restaurantId) {
		Optional<Restaurant> restaurant = restaurantService.readRestaurant(restaurantId);
		return new ResponseEntity<Optional<Restaurant>>(restaurant, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete Restaurant", notes="Delete a restaurant with an restaurantId",nickname = "deleteRestaurant")
	@RequestMapping(value = "/{restaurantId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRestaurant(@PathVariable("restaurantId") Long restaurantId) {
		Optional<Restaurant> restaurant = restaurantService.deleteRestaurant(restaurantId);
		if(restaurant.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Update Restaurant", notes="Update Restaurant with a restaurantId",nickname = "updateRestaurant")
	@RequestMapping(value = "/{restaurantId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRestaurant(@PathVariable("restaurantId") Long restaurantId, @RequestBody Restaurant restaurant) {
		Optional<Restaurant> updatedRestaurant = restaurantService.updateRestaurant(restaurantId,restaurant);
		if(updatedRestaurant.isPresent()) {
			return new ResponseEntity<Optional<Restaurant>>(updatedRestaurant, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}


}
