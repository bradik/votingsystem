package com.example.votingsystem.web.controler;

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
    protected VoteService voteService;

    @Autowired
    protected MenuService menuService;

    @Before
    public void setUp() {

    }

}
