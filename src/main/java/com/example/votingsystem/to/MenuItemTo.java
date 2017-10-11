package com.example.votingsystem.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Brad on 10.10.2017.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemTo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mealName;
    private BigDecimal price;
    private Date date;

}
