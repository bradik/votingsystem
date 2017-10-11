package com.example.votingsystem.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Brad on 03.10.2017.
 */
@Entity
@Table(name = "menu")
public class Menu extends AbstractBaseEntity {

    @Column(name = "date")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

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

    public Menu() {

    }

    public Menu(Date date, Restaurant restaurant, Meal meal, BigDecimal price) {
        this.date = date;
        this.restaurant = restaurant;
        this.meal = meal;
        this.price = price;
    }

    public Menu(Restaurant restaurant, Meal meal, BigDecimal price) {
        this.date = new Date();
        this.restaurant = restaurant;
        this.meal = meal;
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
