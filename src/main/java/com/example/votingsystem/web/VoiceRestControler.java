package com.example.votingsystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Brad on 10.10.2017.
 */

@RestController
@RequestMapping(value = VoiceRestControler.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoiceRestControler {

    static final String REST_URL = "/rest/user/bar";

    private final Logger LOG = LoggerFactory.getLogger(getClass());

//    Отдать голос за ресторан
//      •	PUT/user/bar/{id}/voice

}
