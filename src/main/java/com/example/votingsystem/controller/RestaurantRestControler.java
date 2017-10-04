package com.example.votingsystem.controller;

import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Brad on 16.09.2017.
 */

@RestController
@RequestMapping(value = RestaurantRestControler.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestControler {
    static final String REST_URL = "/rest/bar";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    private static final Logger logger = LoggerFactory.getLogger(RestaurantRestControler.class);

    @PostMapping
    public void create(Restaurant restaurant) {
        logger.info("create() request received");

        service.save(restaurant);
    }

    @PutMapping
    public void save(Restaurant restaurant) {
        logger.info("save() request received");

        service.save(restaurant);
    }

    @DeleteMapping
    public void delete(Integer id) {
        logger.info("delete() request received");

        service.delete(id);
    }

}
