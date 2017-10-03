package com.example.votingsystem.repository;

import com.example.votingsystem.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Brad on 16.09.2017.
 */

@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
