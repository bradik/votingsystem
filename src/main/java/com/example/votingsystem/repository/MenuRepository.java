package com.example.votingsystem.repository;

import com.example.votingsystem.model.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository {

    List<Menu> findBy(Integer barId, LocalDate date, Integer mealId, String mealName);

    Menu getItem(Integer barId, String mealName, LocalDate date);

    Menu save(Menu menu);

    void delete(Integer id);

}
