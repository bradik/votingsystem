package com.example.votingsystem.service.impl;

import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.model.User;
import com.example.votingsystem.repository.RestaurantRepository;
import com.example.votingsystem.repository.UserRepository;
import com.example.votingsystem.service.RestaurantService;
import com.example.votingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brad on 03.10.2017.
 */

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository dao;

    @Override
    public Restaurant getById(Integer id) {
        return dao.findOne(id);
    }

    @Override
    public Restaurant getByName(String name) {
        return dao.getByName(name);
    }

    @Override
    public List<Restaurant> getAll() {
        return dao.findAll();
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return dao.save(restaurant);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

}
