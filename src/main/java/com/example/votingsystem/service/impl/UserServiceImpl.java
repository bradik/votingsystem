package com.example.votingsystem.service.impl;

import com.example.votingsystem.model.User;
import com.example.votingsystem.repository.UserRepository;
import com.example.votingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brad on 03.10.2017.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository dao;

    @Override
    public User getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public void save(User user) {
        dao.save(user);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        result = dao.findAll();
        return result;
    }
}
