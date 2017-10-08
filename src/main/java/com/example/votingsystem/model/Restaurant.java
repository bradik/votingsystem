package com.example.votingsystem.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Brad on 03.10.2017.
 */
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    private String address;

    public Restaurant() {

    }

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
