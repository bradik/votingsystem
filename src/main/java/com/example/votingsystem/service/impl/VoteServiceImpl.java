package com.example.votingsystem.service.impl;

import com.example.votingsystem.model.Meal;
import com.example.votingsystem.repository.MealRepository;
import com.example.votingsystem.repository.VoteRepository;
import com.example.votingsystem.service.MealService;
import com.example.votingsystem.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Brad on 08.10.2017.
 */
@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository dao;

    @Override
    public void putVote() {
        
    }
}
