package com.example.votingsystem.web.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Created by Brad on 17.10.2017.
 */

@Getter
@Setter
@NoArgsConstructor
public class VoteResultTo  {

    private LocalDate date;
    protected String barName;
    private Long votes;

    public VoteResultTo(LocalDate date, String barName, Long votes) {
        this.date = date;
        this.barName = barName;
        this.votes = votes;
    }
}
