package com.example.votingsystem.service.impl;

import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.model.User;
import com.example.votingsystem.model.Vote;
import com.example.votingsystem.repository.RestaurantRepository;
import com.example.votingsystem.repository.UserRepository;
import com.example.votingsystem.repository.VoteRepository;
import com.example.votingsystem.service.VoteService;
import com.example.votingsystem.to.VoteResultTo;
import com.example.votingsystem.util.exception.VoteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Brad on 08.10.2017.
 */
@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository dao;

    @Autowired
    private RestaurantRepository daoBar;

    @Autowired
    private UserRepository daoUser;

    @Override
    public List<VoteResultTo> getResultsByDate(LocalDate date) {

        return dao.getResultsByDate(date);
    }

    @Override
    public void putVoteOnDate(Integer barId, Integer userId, LocalDateTime date) {

        //Only one vote counted per user
        //If user votes again the same day:
        //o If it is before 11:00 we asume that he changed his mind.
        //o If it is after 11:00 then it is too late, vote can 't be changed

        Restaurant bar = daoBar.findOne(barId);
        User user = daoUser.findOne(userId);

        Assert.notNull(bar, "bar is't found");
        Assert.notNull(user, "user is't found");

        LocalDate lDate = LocalDate.from(date);
        LocalTime lTime = LocalTime.from(date);

        Vote vote = dao.findByUserIdAndDate(userId, lDate);

        if (vote!=null && bar.equals(vote.getRestaurant())){
            //nothing has changed
            return;
        }

        if (vote!=null && lTime.isAfter(LocalTime.of(11,00))){
            throw new VoteException("The voice cannot be changed after 11 o'clock.");
        }

        if (vote == null){
            vote = new Vote(lDate, user, bar);
        } else{
            vote.setRestaurant(bar);
        }

        dao.save(vote);
    }

    @Override
    public void putVote(Integer barId, Integer userId) {

        putVoteOnDate(barId, userId, LocalDateTime.now());

    }
}
