package com.example.votingsystem.service;

import com.example.votingsystem.model.Menu;

import java.util.List;

/**
 * Created by Brad on 08.10.2017.
 */
public interface MenuService {

    List<Menu> getAll(Integer barId);

    Menu get(Integer barId, String mealName);

    Menu save(Integer barId, String mealName, Long price);

    void deleteAll(Integer barId);

    void delete(Integer barId, String mealName);

}
