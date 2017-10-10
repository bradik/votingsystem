package com.example.votingsystem.service.impl;

import com.example.votingsystem.model.Meal;
import com.example.votingsystem.model.Menu;
import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.repository.MealRepository;
import com.example.votingsystem.repository.MenuRepository;
import com.example.votingsystem.repository.RestaurantRepository;
import com.example.votingsystem.service.MenuService;
import com.example.votingsystem.to.MenuItemTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static java.math.BigDecimal.ZERO;

/**
 * Created by Brad on 08.10.2017.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuDao;

    @Autowired
    private RestaurantRepository restaurantDao;

    @Autowired
    private MealRepository mealDao;

    @Override
    public List<Menu> getAll(Integer barId) {
        return menuDao.findAll(barId);
    }

    @Override
    public Menu get(Integer barId, String mealName) {

        return menuDao.getItem(barId, mealName, new Date());
    }

    @Override
    public Menu save(Integer barId, MenuItemTo item) {

        Menu menu = menuDao.getItem(barId, item.getMealName(), item.getDate() == null ? new Date() : item.getDate());

        if (menu == null) {
            Restaurant restaurant = restaurantDao.getById(barId);
            Meal meal = mealDao.getByName(item.getMealName());
            if (meal == null) {
                meal = new Meal(item.getMealName());
                mealDao.save(meal);
            }

            menu = new Menu(restaurant, meal, ZERO);
        }

        menu.setPrice(item.getPrice());

        return menuDao.save(menu);
    }

    @Override
    public void deleteAll(Integer barId) {

        List<Menu> menuList = menuDao.findAll(barId);
        for (Menu menu : menuList) {
            menuDao.delete(menu.getId());
        }
    }

    @Override
    public void delete(Integer barId, String mealName) {
        Menu menu = menuDao.getItem(barId, mealName, new Date());
        if (menu != null) {
            menuDao.delete(menu.getId());
        }
    }
}
