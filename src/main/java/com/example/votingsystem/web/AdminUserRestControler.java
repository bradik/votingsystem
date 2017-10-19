package com.example.votingsystem.web;

import com.example.votingsystem.model.User;
import com.example.votingsystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by Brad on 16.09.2017.
 */

@RestController
@RequestMapping(value = AdminUserRestControler.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUserRestControler {
    static final String REST_URL = "/rest/admin/users";

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;



    @GetMapping
    public List<User> getAll() {
        LOG.info("getAll() request received");

        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public User get(@PathVariable("id") int id) {
        LOG.info("get() request received");

        return service.getById(id);
    }

    @GetMapping(value = "/by")
    public User getByName(@RequestParam("email") String email) {
        LOG.info("getByName() request received");

        return service.getByEmail(email);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> update(@Valid @RequestBody User user) {
        LOG.info("update() request received");

        User created =  service.save(user);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        LOG.info("delete() request received");
        service.delete(id);
    }
}
