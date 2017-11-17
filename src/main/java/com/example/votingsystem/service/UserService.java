package com.example.votingsystem.service;

import com.example.votingsystem.model.User;

import java.util.List;

/**
 * Created by Brad on 16.09.2017.
 */

public interface UserService {

    User getById(Integer id);

    User getByEmail(String email);

    List<User> getAll();

    User save(User user);

    void delete(int id);

}
