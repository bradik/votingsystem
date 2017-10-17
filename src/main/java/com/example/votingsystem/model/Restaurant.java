package com.example.votingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by Brad on 03.10.2017.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @NotNull
    private String address;

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

}
