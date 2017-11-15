package com.example.votingsystem.service;

import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.model.User;

import java.util.List;

/**
 * Created by Brad on 16.09.2017.
 */

public interface RestaurantService {

    Restaurant getById(Integer id);

    Restaurant getByName(String name);

    List<Restaurant> getAll();

    Restaurant save(Restaurant restaurant);

    void delete(Integer id);

    void evictCache();
}
