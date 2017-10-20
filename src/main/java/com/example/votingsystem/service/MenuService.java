package com.example.votingsystem.service;

import com.example.votingsystem.model.Menu;
import com.example.votingsystem.web.to.MenuItemTo;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Brad on 08.10.2017.
 */
public interface MenuService {

    List<Menu> getAll(Integer barId);

    List<Menu> findBy(Integer barId, LocalDate date, Integer mealId, String mealName);

    Menu get(Integer barId, String mealName);

    Menu save(Integer barId, MenuItemTo item);

    void delete(Integer id);

}
