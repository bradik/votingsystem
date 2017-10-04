package com.example.votingsystem.repository;

import com.example.votingsystem.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Brad on 16.09.2017.
 */

@Transactional(readOnly = true)
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

}
