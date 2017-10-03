package com.example.votingsystem.repository;

import com.example.votingsystem.model.Meal;
import com.example.votingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Brad on 16.09.2017.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User getById(Integer id);

    @Transactional
    @Override
    User save(User user);

    @Transactional
    @Override
    void delete(Integer id);

    List<User> findAll();

}
