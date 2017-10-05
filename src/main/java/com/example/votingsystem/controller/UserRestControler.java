package com.example.votingsystem.controller;

import com.example.votingsystem.model.User;
import com.example.votingsystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public List<User> getAll() {
        logger.info("getAll() request received");

        return service.findAll();
    }

    @GetMapping(value = "/user/{id}")
    public User get(@PathVariable("id") int id) {
        logger.info("get() request received");

        return service.getById(id);
    }

    @GetMapping(value = "/userbyemail/{email}")
    public User getByEmail(@PathVariable("email") String email) {
        logger.info("getByEmail() request received");

        return service.getByEmail(email);
    }

    @PostMapping(value = "/user",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user) {
        logger.info("update() request received");

        service.save(user);
    }

    @DeleteMapping(value = "/user/{id}")
    public void delete(@PathVariable("id") int id) {
        logger.info("delete() request received");
        service.delete(id);
    }
}
