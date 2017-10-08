package com.example.votingsystem.repository;

import com.example.votingsystem.model.Meal;
import com.example.votingsystem.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Brad on 16.09.2017.
 */

@Transactional(readOnly = true)
public interface MealRepository extends JpaRepository<Meal, Integer> {

    Meal getById(Integer id);

    Meal getByName(String name);

    @Transactional
    @Override
    Meal save(Meal meal);

    @Transactional
    @Override
    void delete(Integer id);

}
