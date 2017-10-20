package com.example.votingsystem.service;

import com.example.votingsystem.web.to.VoteResultTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Brad on 08.10.2017.
 */
public interface VoteService {

    void putVoteOnDate(Integer barId, Integer userId, LocalDateTime date);

    void putVote(Integer barId, Integer userId);

    List<VoteResultTo> getResultsByDate(LocalDate date);

}
