package com.example.votingsystem.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Brad on 03.10.2017.
 */
@Entity
@Table(name = "meals")
public class Meal extends AbstractNamedEntity {

    public Meal() {
    }

    public Meal(String name) {
        this.name = name;
    }
}
