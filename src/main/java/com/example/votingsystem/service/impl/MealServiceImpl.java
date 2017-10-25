package com.example.votingsystem.service.impl;

import com.example.votingsystem.model.Meal;
import com.example.votingsystem.repository.MealRepository;
import com.example.votingsystem.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Brad on 08.10.2017.
 */
@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository dao;

    @Override
    public Meal getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public Meal getByName(String name) {
        return dao.getByName(name);
    }

    @Override
    public Meal save(Meal meal) {
        return dao.save(meal);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }
}
