package com.example.votingsystem.service.impl;

import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.model.User;
import com.example.votingsystem.repository.RestaurantRepository;
import com.example.votingsystem.repository.UserRepository;
import com.example.votingsystem.service.RestaurantService;
import com.example.votingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable("bars")
    @Override
    public Restaurant getById(Integer id) {
        return dao.findOne(id);
    }

    @Cacheable("bars")
    @Override
    public Restaurant getByName(String name) {
        return dao.getByName(name);
    }

    @Cacheable("bars")
    @Override
    public List<Restaurant> getAll() {
        return dao.findAll();
    }

    @CacheEvict(value = "bars", allEntries = true)
    @Override
    public Restaurant save(Restaurant restaurant) {
        return dao.save(restaurant);
    }

    @CacheEvict(value = "bars", allEntries = true)
    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @CacheEvict(value = "bars", allEntries = true)
    @Override
    public void evictCache() {
        // only for evict cache
    }
}
