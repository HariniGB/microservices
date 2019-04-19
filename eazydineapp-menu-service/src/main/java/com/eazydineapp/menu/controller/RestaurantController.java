package com.eazydineapp.menu.controller;

import com.eazydineapp.menu.constants.ApiPathConstants;
import com.eazydineapp.menu.dto.RestaurantDTO;
import com.eazydineapp.menu.model.Restaurant;
import com.eazydineapp.menu.service.interfaces.RestaurantService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
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
import java.util.stream.Collectors;

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
		/*	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{restaurantId}")
					.buildAndExpand(savedRestaurant.get().getId()).toUri();
			return ResponseEntity.created(location).build();*/
            return new ResponseEntity<Restaurant>(savedRestaurant.get(), HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}

	@ApiOperation(value = "Read Restaurant", notes="Read a RestaurantDTO with restaurantId",nickname = "readRestaurant")
	@RequestMapping(value = "/{restaurantId}", method = RequestMethod.GET)
	public ResponseEntity<?> readRestaurant(@PathVariable("restaurantId") Long restaurantId) {
		Optional<Restaurant> restaurant = restaurantService.readRestaurant(restaurantId);
		return new ResponseEntity<Optional<Restaurant>>(restaurant, HttpStatus.OK);
	}



	@ApiOperation(value = "Delete RestaurantDTO", notes="Delete a restaurant with an restaurantId",nickname = "deleteRestaurant")
	@RequestMapping(value = "/{restaurantId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRestaurant(@PathVariable("restaurantId") Long restaurantId) {
		Optional<Restaurant> restaurant = restaurantService.deleteRestaurant(restaurantId);
		if(restaurant.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Update Restaurant", notes="Update RestaurantDTO with a restaurantId",nickname = "updateRestaurant")
	@RequestMapping(value = "/{restaurantId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRestaurant(@PathVariable("restaurantId") Long restaurantId, @RequestBody Restaurant restaurant) {
		Optional<Restaurant> updatedRestaurant = restaurantService.updateRestaurant(restaurantId,restaurant);
		if(updatedRestaurant.isPresent()) {
			return new ResponseEntity<Optional<Restaurant>>(updatedRestaurant, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "ReadAll Restaurants of a User", notes="Get All the restaurants for given user, input user uuid required",nickname = "getUserRestaurants")
	@RequestMapping(value = "/", params="uuid" ,method = RequestMethod.GET)
	public ResponseEntity<?> getAllUserRestaurants(@RequestParam("uuid") String uuid) {
		List<Restaurant> userRestaurants =  restaurantService.readAllUserRestaurants(uuid);
		if(userRestaurants!=null) {
            ModelMapper modelMapper = new ModelMapper();
            List<RestaurantDTO> userRestaurantsDTO = userRestaurants.stream().
                    map(source -> modelMapper.map(source, RestaurantDTO.class)).
                    collect(Collectors.toList());
			return new ResponseEntity<List<RestaurantDTO>>(userRestaurantsDTO, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "ReadAll Restaurants", notes="Get All the restaurants",nickname = "getAllRestaurants")
    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public ResponseEntity<?> getAllRestaurants() {
        List<Restaurant> allRestaurants =  restaurantService.readAllRestaurants();
        if(allRestaurants!=null) {
            ModelMapper modelMapper = new ModelMapper();
            List<RestaurantDTO> allRestaurantsDTO = allRestaurants.stream().
                    map(source -> modelMapper.map(source, RestaurantDTO.class)).
                    collect(Collectors.toList());
            return new ResponseEntity<List<RestaurantDTO>>(allRestaurantsDTO, HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Read Restaurant", notes="Read a RestaurantDTO with firebase restaurantId",nickname = "readFirebaseRestaurant")
    @RequestMapping(value = "/", params="firebaseId", method = RequestMethod.GET)
    public ResponseEntity<?> readUIDRestaurant(@RequestParam("firebaseId") String firebaseId) {
        Optional<Restaurant> restaurant = restaurantService.readUIDRestaurant(firebaseId);
        if(restaurant.isPresent()){
            return new ResponseEntity<Restaurant>(restaurant.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

    }

	@ApiOperation(value = "Search Restaurant", notes="Search a Restaurant with name like",nickname = "searchRestaurantWithName")
	@RequestMapping(value = "/search", params="name", method = RequestMethod.GET)
	public ResponseEntity<?> searchNameLikeRestaurants(@RequestParam("name") String name) {
		List<Restaurant> allRestaurants =  restaurantService.filterNameLikeRestaurants(name);
		if(allRestaurants!=null) {
			ModelMapper modelMapper = new ModelMapper();
			List<RestaurantDTO> allRestaurantsDTO = allRestaurants.stream().
					map(source -> modelMapper.map(source, RestaurantDTO.class)).
					collect(Collectors.toList());
			return new ResponseEntity<List<RestaurantDTO>>(allRestaurantsDTO, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Search Restaurant", notes="Search a Restaurant with zipcode",nickname = "searchRestaurantWithZipCode")
	@RequestMapping(value = "/search", params="zipcode", method = RequestMethod.GET)
	public ResponseEntity<?> searchZipCodeRestaurants(@RequestParam("zipcode") int zipcode) {
		List<Restaurant> allRestaurants =  restaurantService.filterZipCodeRestaurants(zipcode);
		if(allRestaurants!=null) {
			ModelMapper modelMapper = new ModelMapper();
			List<RestaurantDTO> allRestaurantsDTO = allRestaurants.stream().
					map(source -> modelMapper.map(source, RestaurantDTO.class)).
					collect(Collectors.toList());
			return new ResponseEntity<List<RestaurantDTO>>(allRestaurantsDTO, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}


}
