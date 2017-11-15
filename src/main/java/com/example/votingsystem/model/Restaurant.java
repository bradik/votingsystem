package com.example.votingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
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
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @NotNull
    private String address;

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

}
