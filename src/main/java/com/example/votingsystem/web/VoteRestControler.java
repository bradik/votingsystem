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

        //Отдать голос за ресторан
        //	PUT/user/bar/{id}/vote
        //Only one vote counted per user
        //If user votes again the same day:
        //o If it is before 11:00 we asume that he changed his mind.
        //o If it is after 11:00 then it is too late, vote can 't be changed
        //TODO


        return null;
    }

}
