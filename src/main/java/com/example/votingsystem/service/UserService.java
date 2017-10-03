package com.example.votingsystem.service;

import com.example.votingsystem.model.User;

import java.util.List;

/**
 * Created by Brad on 16.09.2017.
 */

public interface UserService {

    User getById(Integer id);

    void save(User route);

    void delete(Integer id);

    List<User> findAll();

}
