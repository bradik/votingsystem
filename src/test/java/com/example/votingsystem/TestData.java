package com.example.votingsystem;

import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.model.Roles;
import com.example.votingsystem.model.User;

import java.time.LocalDate;

/**
 * Created by Brad on 18.10.2017.
 */
public class TestData {

    public static final LocalDate YESTERDAY = LocalDate.now().minusDays(1);

    public static final User ADMIN = new User(1, "admin@gmail.com", "admin", Roles.ADMIN);
    public static final User USER = new User(2, "user@gmail.com", "user", Roles.USER);

    public static final Restaurant TEST_BAR_1 = new Restaurant("Пиворама", "Большевиков  проспект, 18 к2");
    public static final Restaurant TEST_BAR_2 = new Restaurant("Бахрома", "Наставников, проспек, 24 к1");

}
