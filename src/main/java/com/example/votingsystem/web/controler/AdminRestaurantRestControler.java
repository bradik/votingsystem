package com.example.votingsystem.web.controler;

import com.example.votingsystem.model.Restaurant;
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

import static com.example.votingsystem.util.ValidationUtil.checkIdConsistent;
import static com.example.votingsystem.util.ValidationUtil.checkNew;

/**
 * Created by Brad on 16.09.2017.
 */

@RestController
@RequestMapping(value = AdminRestaurantRestControler.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantRestControler {
    static final String REST_URL = "/rest/admin/bars";

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;


    @GetMapping
    public List<Restaurant> getAll() {
        LOG.info("getAll() request received");

        return restaurantService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        LOG.info("get() request received");

        return restaurantService.getById(id);
    }

    @GetMapping(value = "/by")
    public Restaurant getByName(@RequestParam("name") String name) {
        LOG.info("getByName() request received");

        return restaurantService.getByName(name);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        LOG.info("create() request received");
        checkNew(restaurant);
        Restaurant created = restaurantService.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        LOG.info("update " + restaurant);
        checkIdConsistent(restaurant, id);
        restaurantService.save(restaurant);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        LOG.info("delete() request received");
        restaurantService.delete(id);
    }

}
