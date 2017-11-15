package com.example.votingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Brad on 03.10.2017.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu")
public class Menu extends AbstractBaseEntity {

    @Column(name = "date")
    @NotNull
    //@Type(type="org.joda.time.contrib.hibernate.PersistentLocalDate")
    //@DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDate date = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Meal meal;

    @Column(name = "price")
    private BigDecimal price;

    public Menu(Restaurant restaurant, Meal meal, BigDecimal price, LocalDate date) {

        this.restaurant = restaurant;
        this.meal = meal;
        this.price = price;
        this.date = date;
    }
}
