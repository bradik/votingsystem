package com.example.votingsystem.web;

import com.example.votingsystem.model.Menu;
import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.service.MealService;
import com.example.votingsystem.service.MenuService;
import com.example.votingsystem.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by Brad on 16.09.2017.
 */

@RestController
@RequestMapping(value = RestaurantRestControler.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestControler {
    static final String REST_URL = "/rest/admin/bars";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    private static final Logger logger = LoggerFactory.getLogger(RestaurantRestControler.class);

    @GetMapping
    public List<Restaurant> getAll() {
        logger.info("getAll() request received");

        return restaurantService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        logger.info("get() request received");

        return restaurantService.getById(id);
    }

    @GetMapping(value = "/by")
    public Restaurant getByName(@RequestParam("name") String name) {
        logger.info("getByName() request received");

        return restaurantService.getByName(name);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> update(@RequestBody Restaurant restaurant) {
        logger.info("update() request received");

        Restaurant created = restaurantService.save(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        logger.info("delete() request received");
        restaurantService.delete(id);
    }

    @PostMapping(value = "/{id}/meals", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> updateBarsMenuItem(@PathVariable("id") Integer barId, @RequestBody String mealName, @RequestBody Long price) {
        logger.info("updateBarsMenuItem() request received");

        Menu created = menuService.save(barId, mealName, price);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/bars/{id}/meals/{name}")
                .buildAndExpand(created.getRestaurant().getId(),created.getMeal().getName()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }


}
