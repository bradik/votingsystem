package com.example.votingsystem.web;

import com.example.votingsystem.AuthorizedUser;
import com.example.votingsystem.model.Vote;
import com.example.votingsystem.service.UserService;
import com.example.votingsystem.service.VoteService;
import com.example.votingsystem.to.VoteResultTo;
import com.example.votingsystem.to.VoteTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.example.votingsystem.util.DateTimeUtil.DATE_TIME_PATTERN;

/**
 * Created by Brad on 10.10.2017.
 */

@RestController
@RequestMapping(value = VoteRestControler.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestControler {

    static final String REST_URL = "/rest/user/votes";

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    protected VoteService voteService;

    @GetMapping(value = "/results", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VoteResultTo> getResults() {

        return voteService.getResultsByDate(LocalDate.now());
    }

    @GetMapping(value = "/results/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VoteResultTo> getResultsByDate(@DateTimeFormat(pattern = DATE_TIME_PATTERN) @RequestParam("date") LocalDate date) {

        return voteService.getResultsByDate(date);
    }

    @PutMapping(value = "/bar/{barid}")
    public void putVote(@PathVariable("barid") Integer barId) {
        voteService.putVote(barId, AuthorizedUser.id());
    }

}
