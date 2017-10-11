package com.example.votingsystem.repository;

import com.example.votingsystem.model.Meal;
import com.example.votingsystem.model.Menu;
import org.hibernate.sql.Select;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Brad on 16.09.2017.
 */

@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query("select m from #{#entityName} as m where m.restaurant.id = ?1")
    List<Menu> findAll(Integer barId);

    @Query("select m from  #{#entityName} as m where m.restaurant.id = ?1 and m.meal.name = ?2 and m.date = ?3")
    Menu getItem(Integer barId, String mealName, Date date);

    @Transactional
    @Override
    Menu save(Menu menu);

    @Transactional
    @Override
    void delete(Integer id);

}
