package com.example.votingsystem.service;

import com.example.votingsystem.model.Menu;
import com.example.votingsystem.to.MenuItemTo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Brad on 08.10.2017.
 */
public interface MenuService {

    List<Menu> getAll(Integer barId);

    Menu get(Integer barId, String mealName);

    Menu save(Integer barId, MenuItemTo item);

    void deleteAll(Integer barId);

    void delete(Integer barId, String mealName);

}