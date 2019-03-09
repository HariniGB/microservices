package com.eazydineapp.menu.controller;


import com.eazydineapp.menu.constants.ApiPathConstants;
import com.eazydineapp.menu.constants.ServiceConstants;
import com.eazydineapp.menu.exception.GenericExceptionHandler;
import com.eazydineapp.menu.model.Item;
import com.eazydineapp.menu.service.interfaces.ItemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiPathConstants.ITEM_RESOURCE)
@CrossOrigin(origins = {"*"})
public class ItemController {

    @Autowired
    private ItemService itemService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @ApiOperation(value = "Create Item", notes="Create a menu item",nickname = "createItem")
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createItem(@RequestBody Item item) {

        Optional<Item> savedItem = itemService.createItem(item);
        if (savedItem.isPresent()) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{itemId}")
                    .buildAndExpand(savedItem.get().getId()).toUri();
            return ResponseEntity.created(location).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @ApiOperation(value = "Read Item", notes="Read an item with an itemId",nickname = "readItem")
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    public ResponseEntity<?> readItem(@PathVariable("itemId") Long itemId) {
        Optional<Item> item = itemService.readItem(itemId);
        return new ResponseEntity<Optional<Item>>(item, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Item", notes="Delete an item with an itemId",nickname = "deleteItem")
    @RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteItem(@PathVariable("itemId") Long itemId) {
        Optional<Item> item = itemService.deleteItem(itemId);
        if(item.isPresent()) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Update Item", notes="Update an item with an itemId",nickname = "updateItem")
    @RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateItem(@PathVariable("itemId") Long itemId, @RequestBody Item item) {
        Optional<Item> updatedItem = itemService.updateItem(itemId,item);
        if(updatedItem.isPresent()) {
            return new ResponseEntity<Optional<Item>>(updatedItem, HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "ReadAll Items of a Menu", notes="Get All the items for given menu from a restaurant, input menuId required",nickname = "readMenuItems")
    @RequestMapping(value = "/", params="menuId" ,method = RequestMethod.GET)
    public ResponseEntity<?> getMenuItems(@RequestParam("menuId") Long menuId) {
        List<Item> menuItems =  itemService.readAllMenuItems(menuId);
        if(menuItems!=null) {
            return new ResponseEntity<List<Item>>(menuItems, HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}