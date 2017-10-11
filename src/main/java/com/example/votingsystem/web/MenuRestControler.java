package com.example.votingsystem.web;

import com.example.votingsystem.service.MenuService;
import com.example.votingsystem.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Brad on 10.10.2017.
 */

@RestController
@RequestMapping(value = MenuRestControler.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestControler {

    static final String REST_URL = "/rest/bars";
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;


//    Получить меню ресторана на текущую дату
//      •	GET/user/bars/{id}/meals
//    Получить пункт меню ресторана на текущую дату
//      •	GET/user/bars/{id}/meals/{name}



}
