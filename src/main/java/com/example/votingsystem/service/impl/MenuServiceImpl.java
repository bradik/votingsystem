package com.example.votingsystem.service.impl;

import com.example.votingsystem.model.Meal;
import com.example.votingsystem.model.Menu;
import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.repository.MealRepository;
import com.example.votingsystem.repository.MenuRepository;
import com.example.votingsystem.repository.jpa.JpaMenuRepository;
import com.example.votingsystem.repository.RestaurantRepository;
import com.example.votingsystem.service.MenuService;
import com.example.votingsystem.web.to.MenuItemTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        return menuDao.findBy(barId, null, null, null);
    }

    @Override
    public List<Menu> findBy(Integer barId, LocalDate date, Integer mealId, String mealName) {

        return menuDao.findBy(barId, date, mealId, mealName);
    }

    @Override
    public Menu get(Integer barId, String mealName) {

        return menuDao.getItem(barId, mealName, LocalDate.now());
    }

    @Override
    public Menu save(Integer barId, MenuItemTo item) {

        LocalDate date = item.getDate() == null ? LocalDate.now() : item.getDate();
        Menu menu = menuDao.getItem(barId, item.getMealName(), date);

        if (menu == null) {
            Restaurant restaurant = restaurantDao.getById(barId);
            Meal meal = mealDao.getByName(item.getMealName());
            if (meal == null) {
                meal = new Meal(item.getMealName());
                mealDao.save(meal);
            }

            menu = new Menu(restaurant, meal, ZERO, date);
        }

        menu.setPrice(item.getPrice());

        return menuDao.save(menu);
    }

    @Override
    public void delete(Integer id) {
        menuDao.delete(id);

    }
}
