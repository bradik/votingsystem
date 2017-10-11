package com.example.votingsystem.repository;

import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Brad on 16.09.2017.
 */

@RestController
@Transactional(readOnly = true)
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

    Restaurant getById(Integer id);

    Restaurant getByName(String name);

    List<Restaurant> findAll();

    @Transactional
    @Override
    Restaurant save(Restaurant user);

    @Transactional
    @Override
    void delete(Integer id);

}
