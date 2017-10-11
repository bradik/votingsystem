package com.example.votingsystem.web;

import com.example.votingsystem.model.Vote;
import com.example.votingsystem.to.VoteTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Brad on 10.10.2017.
 */

@RestController
@RequestMapping(value = VoteRestControler.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestControler {

    static final String REST_URL = "/rest/user/bar";

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @PutMapping(value = "/{id}/vote",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> putVote(@PathVariable("id") Integer barId, @RequestBody VoteTo voteTo) {
        //TODO
        //    Отдать голос за ресторан
        //      •	PUT/user/bar/{id}/vote
        return null;
    }

}
