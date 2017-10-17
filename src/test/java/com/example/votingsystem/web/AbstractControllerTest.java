package com.example.votingsystem.web;

import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.model.Roles;
import com.example.votingsystem.model.User;
import com.example.votingsystem.service.MenuService;
import com.example.votingsystem.service.RestaurantService;
import com.example.votingsystem.service.UserService;
import com.example.votingsystem.service.VoteService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
abstract public class AbstractControllerTest {

    static final LocalDate YESTERDAY = LocalDate.now().minusDays(1);

    static final User ADMIN = new User(1, "admin@gmail.com", "admin", Roles.ADMIN);
    static final User USER = new User(2, "user@gmail.com", "user", Roles.USER);

    static final Restaurant TEST_BAR_1 = new Restaurant("Пиворама", "Большевиков  проспект, 18 к2");
    static final Restaurant TEST_BAR_2 = new Restaurant("Бахрома", "Наставников, проспек, 24 к1");

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected UserService userService;

    @Autowired
    protected RestaurantService restaurantService;

    @Autowired
    protected MenuService menuService;

    @Autowired
    protected VoteService voteService;

    @Before
    public void setUp() {

    }

}
