package com.example.votingsystem.service;

import com.example.votingsystem.model.Meal;
import com.example.votingsystem.model.Menu;

import java.util.List;

/**
 * Created by Brad on 08.10.2017.
 */
public interface MealService {

    Meal getById(Integer id);

    Meal getByName(String name);

    Meal save(Meal meal);

    void delete(Integer id);

}
