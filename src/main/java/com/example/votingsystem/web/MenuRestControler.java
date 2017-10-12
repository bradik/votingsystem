package com.example.votingsystem.web;

import com.example.votingsystem.model.Menu;
import com.example.votingsystem.service.MenuService;
import com.example.votingsystem.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Brad on 10.10.2017.
 */

@RestController
@RequestMapping(value = MenuRestControler.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestControler {

    static final String REST_URL = "/rest/user/bars";
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/{barid}/meals")
    public List<Menu> getAll(@PathVariable(value = "barid") Integer barId) {
        LOG.info("getAll() request received");
        LOG.debug("barid = {}", barId);

        return menuService.getAll(barId);
    }

    @GetMapping(value = "/{barid}/meals/by")
    public List<Menu> getAllBy(@PathVariable(value = "barid") Integer barId,
                               @RequestParam(value = "id", required = false) Integer mealId,
                               @RequestParam(value = "name", required = false) String mealName) {
        LOG.info("getAll() request received");
        LOG.debug("barid = {}, mealid = {}, mealname = {}", barId, mealId, mealName);

        return menuService.findBy(barId, LocalDate.now(), mealId, mealName);
    }


}
