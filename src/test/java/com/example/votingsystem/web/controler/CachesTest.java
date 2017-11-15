package com.example.votingsystem.web.controler;

import com.example.votingsystem.service.RestaurantService;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.votingsystem.TestData.TEST_BAR_1;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class CachesTest {

    @Autowired
    protected RestaurantService restaurantService;

    @Test
    public void cachesTest() {

    }
}
