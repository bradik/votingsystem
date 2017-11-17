package com.example.votingsystem.repository;

import com.example.votingsystem.model.Meal;
import com.example.votingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Brad on 16.09.2017.
 */

@RestController
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

    User getById(Integer id);

    User findByEmail(String email);

    List<User> findAll();

    @Transactional
    @Override
    User save(User user);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

}
