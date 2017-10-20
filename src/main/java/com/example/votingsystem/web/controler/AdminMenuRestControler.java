package com.example.votingsystem.web.controler;

import com.example.votingsystem.model.Menu;
import com.example.votingsystem.service.MenuService;
import com.example.votingsystem.web.to.MenuItemTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static com.example.votingsystem.util.DateTimeUtil.DATE_TIME_PATTERN;

/**
 * Created by Brad on 10.10.2017.
 */

@RestController
@RequestMapping(value = AdminMenuRestControler.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMenuRestControler {

    static final String REST_URL = "/rest/admin/bars";

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService menuService;

    @PostMapping(value = "/{id}/meals", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> updateBarsMenuItem(@PathVariable("id") Integer barId, @RequestBody MenuItemTo itemTo) {
        LOG.info("updateBarsMenuItem() request received");
        LOG.debug("barid = {}, itemTo = {}", barId, itemTo);

        Menu created = menuService.save(barId, itemTo);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/bars/{idBar}/meals/{name}")
                .buildAndExpand(created.getRestaurant().getId(), created.getMeal().getName()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{barid}/meals")
    public ResponseEntity<Integer> deleteMenuItems(@PathVariable("barid") Integer barId) {

        LOG.info("deleteMenuItems() request received");
        LOG.debug("barid = {}", barId);

        List<Menu> menuList = menuService.getAll(barId);
        for (Menu menu : menuList) {
            menuService.delete(menu.getId());
        }

        return ResponseEntity.ok(menuList.size());
    }

    @DeleteMapping(value = "/{barid}/meals/by")
    public ResponseEntity<Integer> deleteMenuItemsBy(@PathVariable("barid") Integer barId,
                                                     @DateTimeFormat(pattern = DATE_TIME_PATTERN)
                                                     @RequestParam(value = "date", required = false) LocalDate date,
                                                     @RequestParam(value = "id", required = false) Integer mealId,
                                                     @RequestParam(value = "name", required = false) String mealName) {

        LOG.info("deleteMenuItemsBy() request received");
        LOG.debug("barid = {}, date={}, mealid = {}, mealname = {}", barId, date, mealId, mealName);

        List<Menu> menuList = menuService.findBy(barId, date, mealId, mealName);
        for (Menu menu : menuList) {
            menuService.delete(menu.getId());
        }

        return ResponseEntity.ok(menuList.size());
    }
}
