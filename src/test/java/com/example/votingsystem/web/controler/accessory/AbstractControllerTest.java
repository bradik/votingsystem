package com.example.votingsystem.web.controler.accessory;

import com.example.votingsystem.service.MenuService;
import com.example.votingsystem.service.RestaurantService;
import com.example.votingsystem.service.UserService;
import com.example.votingsystem.service.VoteService;
import com.example.votingsystem.repository.JpaUtil;
import com.example.votingsystem.util.MessageUtil;
import com.example.votingsystem.util.exception.ErrorType;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.filter.CharacterEncodingFilter;


import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
abstract public class AbstractControllerTest {

    private static final Logger log = LoggerFactory.getLogger("result");

    public static final ResultHandler MOCK_RESULT_HANDLER = new ResultHandler() {
        @Override
        public void handle(MvcResult result) throws Exception {

        }
    };

    private static StringBuilder results = new StringBuilder();

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

    @Rule
    // http://stackoverflow.com/questions/14892125/what-is-the-best-practice-to-determine-the-execution-time-of-the-bussiness-relev
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("%-95s %7d", description.getDisplayName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result).append('\n');
            log.info(result + " ms\n");
        }
    };

    @AfterClass
    public static void printResult() {
        log.info("\n-------------------------------------------------------------------------------------------------------" +
                "\nTest                                                                                       Duration, ms" +
                "\n-------------------------------------------------------------------------------------------------------\n" +
                results +
                "-------------------------------------------------------------------------------------------------------\n");
        results.setLength(0);
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

    public ResultHandler print() {
        //return MOCK_RESULT_HANDLER;
        return MockMvcResultHandlers.print();
    }
}
