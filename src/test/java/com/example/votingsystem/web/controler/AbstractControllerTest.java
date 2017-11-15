package com.example.votingsystem.web.controler;

import com.example.votingsystem.service.MenuService;
import com.example.votingsystem.service.RestaurantService;
import com.example.votingsystem.service.UserService;
import com.example.votingsystem.service.VoteService;
import com.example.votingsystem.repository.JpaUtil;
import com.example.votingsystem.util.MessageUtil;
import com.example.votingsystem.util.exception.ErrorType;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;


import java.util.Locale;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
abstract public class AbstractControllerTest {

    @Autowired
    JpaUtil jpaUtil;

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

    @Autowired
    protected MessageUtil messageUtil;

    @Before
    public void setUp() {
        restaurantService.evictCache();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    protected String getMessage(String code) {
        return messageUtil.getMessage(code, Locale.ENGLISH);
    }

    public ResultMatcher errorType(ErrorType type) {
        return jsonPath("$.type").value(type.name());
    }

    public ResultMatcher jsonMessage(String path, String code) {
        return jsonPath(path).value(getMessage(code));
    }

}
