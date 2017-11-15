package com.example.votingsystem.repository.impl;

import com.example.votingsystem.model.Menu;
import com.example.votingsystem.repository.MenuRepository;
import com.example.votingsystem.repository.jpa.JpaMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MenuRepositoryImpl implements MenuRepository {

    @Autowired
    private JpaMenuRepository repository;

    @Override
    public List<Menu> findBy(Integer barId, LocalDate date, Integer mealId, String mealName) {

        return repository.findBy(barId, date, mealId, mealName);
    }

    @Override
    public Menu getItem(Integer barId, String mealName, LocalDate date) {

        return repository.getItem(barId, mealName, date);
    }

    @Override
    public Menu save(Menu menu) {

        return repository.save(menu);
    }

    @Override
    public void delete(Integer id) {

        repository.delete(id);
    }
}
