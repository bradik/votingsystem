package com.example.votingsystem.web.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteTo {

    private static final long serialVersionUID = 1L;

    private Date date;
    private Integer barId;
    private Integer userId;

}
