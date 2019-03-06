package com.eazydineapp.menu.controller;

import com.eazydineapp.menu.constants.ApiPathConstants;
import com.eazydineapp.menu.model.Table;
import com.eazydineapp.menu.service.interfaces.TableService;
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
@RequestMapping(ApiPathConstants.TABLE_RESOURCE)
@CrossOrigin(origins = {"*"})
public class TableController {

    @Autowired
    TableService tableService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @ApiOperation(value = "Create Table", notes="Create a Table",nickname = "createTable")
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTable(@RequestBody Table table) {

        Optional<Table> savedTable = tableService.createTable(table);
        if (savedTable.isPresent()) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{tableId}")
                    .buildAndExpand(savedTable.get().getId()).toUri();
            return ResponseEntity.created(location).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @ApiOperation(value = "Read Table", notes="Read a Table with a tableId",nickname = "readTable")
    @RequestMapping(value = "/{tableId}", method = RequestMethod.GET)
    public ResponseEntity<?> readTable(@PathVariable("tableId") Long tableId) {
        Optional<Table> table = tableService.readTable(tableId);
        return new ResponseEntity<Optional<Table>>(table, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Table", notes="Delete a Table with a tableId",nickname = "deleteTable")
    @RequestMapping(value = "/{tableId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTable(@PathVariable("tableId") Long tableId) {
        Optional<Table> table = tableService.deleteTable(tableId);
        if(table.isPresent()) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Update Table", notes="Update Table with a tableId",nickname = "updateTable")
    @RequestMapping(value = "/{tableId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTable(@PathVariable("tableId") Long tableId, @RequestBody Table table) {
        Optional<Table> updateTable = tableService.updateTable(tableId, table);
        if(updateTable.isPresent()) {
            return new ResponseEntity<Optional<Table>>(updateTable, HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Read All Tables of a Restaurant", notes="Get All the Tables for given restaurant, input restaurant required",nickname = "getRestaurantTables")
    @RequestMapping(value = "/", params="restaurantId" ,method = RequestMethod.GET)
    public ResponseEntity<?> getRestaurantTables(@RequestParam("restaurantId") Long restaurantId) {
        List<Table> restaurantTables =  tableService.readAllRestaurantTables(restaurantId);
        if(restaurantTables!=null) {
            return new ResponseEntity<List<Table>>(restaurantTables, HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
