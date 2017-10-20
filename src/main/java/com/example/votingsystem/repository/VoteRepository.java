package com.example.votingsystem.repository;

import com.example.votingsystem.model.Vote;
import com.example.votingsystem.web.to.VoteResultTo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Brad on 16.09.2017.
 */

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {


    @Query("select new com.example.votingsystem.web.to.VoteResultTo(v.date, v.restaurant.name, count(v.id))" +
            " from Vote as v where v.date = :date group by v.restaurant.id, v.date")
    List<VoteResultTo> getResultsByDate(@Param("date") LocalDate date);


    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select v from Vote as v where v.user.id = :userid and v.date = :date")
    Vote findByUserIdAndDate(@Param("userid") Integer userId, @Param("date") LocalDate date);

    @Transactional
    @Override
    Vote save(Vote vote);

    @Transactional
    @Override
    void delete(Integer id);
}
