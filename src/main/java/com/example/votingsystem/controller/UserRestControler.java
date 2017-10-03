package com.example.votingsystem.controller;

import com.example.votingsystem.model.User;
import com.example.votingsystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Brad on 16.09.2017.
 */

@RestController
@RequestMapping(value = UserRestControler.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestControler {
    static final String REST_URL = "/rest/admin";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    private static final Logger logger = LoggerFactory.getLogger(UserRestControler.class);

    @GetMapping(value = "/users")
    public List<User> getRouteFilter() {
        logger.info("getRouteFilter() request received");

        return service.findAll();
    }
}
