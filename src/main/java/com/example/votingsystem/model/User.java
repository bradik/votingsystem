package com.example.votingsystem.model;

import javax.persistence.*;

/**
 * Created by Brad on 03.10.2017.
 */

@Entity
@Table(name = "users")
public class User extends AbstractBaseEntity {

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Roles role;

    public User() {

    }

    public User(String email, String password, Roles role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(Integer id, String email, String password, Roles role) {
        super(id);
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

}
