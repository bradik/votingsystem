package com.example.votingsystem;

import com.example.votingsystem.matcher.BeanMatcher;
import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.model.Roles;
import com.example.votingsystem.model.User;

import java.time.LocalDate;

/**
 * Created by Brad on 18.10.2017.
 */
public class TestData {

    public static final LocalDate YESTERDAY = LocalDate.now().minusDays(1);

    public static final int USER_START_ID = 1;
    public static final User ADMIN = new User(USER_START_ID, "admin@gmail.com", "admin", Roles.ADMIN);
    public static final User USER = new User(USER_START_ID + 1, "user@gmail.com", "user", Roles.USER);
    //new users
    public static final User NEW_USER_1 = new User(null, "user1@gmail.com", "user1", Roles.USER);
    public static final User NEW_USER_2 = new User(null, "user2@gmail.com", "user2", Roles.USER);
    public static final User NEW_USER_EMAIL_DUBLICATE = new User(null, "user@gmail.com", "user4", Roles.USER);

    //to AdminRestaurantRestControlerTest
    public static final Restaurant TEST_BAR_1 = new Restaurant("Пиворама", "Большевиков  проспект, 18 к2");
    public static final Restaurant TEST_BAR_2 = new Restaurant("Бахрома", "Наставников, проспек, 24 к1");
    //to AdminMenuRestControlerTest
    public static final Restaurant TEST_BAR_3 = new Restaurant("Лучший ресторан", "пр. Узников комунизма, 18 к2");
    public static final Restaurant TEST_BAR_4 = new Restaurant("Семь красавиц", "ул Банкиров, 24 к1");
    //to MenuRestControlerTest
    public static final Restaurant TEST_BAR_5 = new Restaurant("Семь сказок", "ул Броневого, 34 к1");
    //to VoteRestControlerTest
    public static final Restaurant TEST_BAR_6 = new Restaurant("Лучший ресторан", "пр. Узников комунизма, 18 к2");
    public static final Restaurant TEST_BAR_7 = new Restaurant("Семь красавиц", "ул Банкиров, 24 к1");

    public static final BeanMatcher<User> USER_MATCHER = BeanMatcher.of(User.class);

}
