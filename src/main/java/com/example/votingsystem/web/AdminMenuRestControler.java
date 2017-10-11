package com.example.votingsystem.web;

import com.example.votingsystem.model.Menu;
import com.example.votingsystem.service.MenuService;
import com.example.votingsystem.service.RestaurantService;
import com.example.votingsystem.to.MenuItemTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Created by Brad on 10.10.2017.
 */

@RestController
@RequestMapping(value = AdminMenuRestControler.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMenuRestControler {

    static final String REST_URL = "/rest/admin/bars";

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    @PostMapping(value = "/{id}/meals", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> updateBarsMenuItem(@PathVariable("id") Integer barId, @RequestBody MenuItemTo itemTo) {
        LOG.info("updateBarsMenuItem() request received");

        Menu created = menuService.save(barId, itemTo);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/bars/{idBar}/meals/{name}")
                .buildAndExpand(created.getRestaurant().getId(),created.getMeal().getName()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{idBar}}/meals")
    public void deleteMenuItems(@PathVariable("idBar") Integer idBar) {
        LOG.info("deleteMenuItems() request received");
        //TODO
    }

    @DeleteMapping(value = "/{idBar}}/meals/{idMeal}")
    public void deleteMenuItem(@PathVariable("idBar") Integer idBar, @PathVariable("idMeal") Integer idMeal) {
        LOG.info("deleteMenuItem() request received");
        //TODO
    }

}
