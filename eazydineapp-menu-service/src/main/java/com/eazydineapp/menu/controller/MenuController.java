package com.eazydineapp.menu.controller;

import com.eazydineapp.menu.constants.ApiPathConstants;
import com.eazydineapp.menu.model.Item;
import com.eazydineapp.menu.model.Menu;
import com.eazydineapp.menu.service.interfaces.ItemService;
import com.eazydineapp.menu.service.interfaces.MenuService;
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
@RequestMapping(ApiPathConstants.MENU_RESOURCE)
@CrossOrigin(origins = {"*"})
public class MenuController {

	@Autowired
	MenuService menuService;

    @Autowired
    ItemService itemService;


    private final Logger log = LoggerFactory.getLogger(this.getClass());


	@ApiOperation(value = "Create Menu", notes="Create a menu",nickname = "createMenu")
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createMenu(@RequestBody Menu menu) {

		Optional<Menu> savedMenu = menuService.createMenu(menu);
		if (savedMenu.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{menuId}")
					.buildAndExpand(savedMenu.get().getId()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}

	@ApiOperation(value = "Read Menu", notes="Read an menu with a menuId",nickname = "readMenu")
	@RequestMapping(value = "/{menuId}", method = RequestMethod.GET)
	public ResponseEntity<?> readItem(@PathVariable("menuId") Long menuId) {
		Optional<Menu> menu = menuService.readMenu(menuId);
		return new ResponseEntity<Optional<Menu>>(menu, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete Menu", notes="Delete a menu with an menuId",nickname = "deleteMenu")
	@RequestMapping(value = "/{menuId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteMenu(@PathVariable("menuId") Long menuId) {
		Optional<Menu> menu = menuService.deleteMenu(menuId);
		if(menu.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Update Menu", notes="Update Menu with a menuId",nickname = "updateMenu")
	@RequestMapping(value = "/{menuId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateMenu(@PathVariable("menuId") Long menuId, @RequestBody Menu menu) {
		Optional<Menu> updatedMenu = menuService.updateMenu(menuId,menu);
		if(updatedMenu.isPresent()) {
			return new ResponseEntity<Optional<Menu>>(updatedMenu, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "ReadAll Menus of a RestaurantDTO", notes="Get All the menus for given restaurant, input restaurant required",nickname = "getRestaurantMenus")
	@RequestMapping(value = "/", params="restaurantId" ,method = RequestMethod.GET)
	public ResponseEntity<?> getRestaurantMenus(@RequestParam("restaurantId") Long restaurantId) {
		List<Menu> restaurantMenus =  menuService.readAllRestaurantMenu(restaurantId);
		if(restaurantMenus!=null) {
			return new ResponseEntity<List<Menu>>(restaurantMenus, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
/*
    @ApiOperation(value = "Add Item to a Menu", notes="Add Item to a Menu with a menuId",nickname = "addItemMenu")
    @RequestMapping(value = "/{menuId}/items/", method = RequestMethod.POST)
    public ResponseEntity<?> addItemtoMenu(@PathVariable("menuId") Long menuId, @RequestBody Item item) {
        Optional<Menu> menu = menuService.readMenu(menuId);
        if(menu.isPresent()){
            Optional<Item> savedItem = itemService.createItem(item);
            if(savedItem.isPresent()) {
                menu.get().getItems().add(savedItem.get());
                Optional<Menu> updatedMenu = menuService.updateMenu(menuId,menu.get());
                return new ResponseEntity<Optional<Menu>>(updatedMenu, HttpStatus.OK);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

*/

}
