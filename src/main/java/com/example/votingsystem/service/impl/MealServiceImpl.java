package com.example.votingsystem.service.impl;

import com.example.votingsystem.model.Meal;
import com.example.votingsystem.repository.MealRepository;
import com.example.votingsystem.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by Brad on 08.10.2017.
 */
@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository dao;

    @Cacheable("meals")
    @Override
    public Meal getById(Integer id) {
        return dao.getById(id);
    }

    @Cacheable("meals")
    @Override
    public Meal getByName(String name) {
        return dao.getByName(name);
    }

    @CacheEvict(value = "meals", allEntries = true)
    @Override
    public Meal save(Meal meal) {
        return dao.save(meal);
    }

    @CacheEvict(value = "meals", allEntries = true)
    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @CacheEvict(value = "meals", allEntries = true)
    @Override
    public void evictCache() {
        // only for evict cache
    }
}
