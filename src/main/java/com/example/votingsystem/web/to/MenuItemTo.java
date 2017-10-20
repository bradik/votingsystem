package com.example.votingsystem.web.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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
    private LocalDate date;


    @Override
    public String toString() {
        return "MenuItemTo{" +
                "mealName='" + mealName + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
