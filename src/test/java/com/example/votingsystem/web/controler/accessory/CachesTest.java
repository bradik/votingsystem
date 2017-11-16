package com.example.votingsystem.web.controler.accessory;

import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.service.RestaurantService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles(value = "test")
public class CachesTest {

    private static final String NAME_1 = "new bar";
    private static final String NAME_2 = "test bar";
    //@Autowired
    protected RestaurantService restaurantService;

    //@Test
    public void cachesTest() {
        //usually not use
        //for work this test need delete @CacheEvict in method save at class RestaurantServiceImpl

        Restaurant testBar = new Restaurant(NAME_1, "any address");

        restaurantService.save(testBar);

        Restaurant testBar1 =  restaurantService.getById(testBar.getId());

        testBar.setName(NAME_2);

        restaurantService.save(testBar);

        Restaurant testBar2 =  restaurantService.getById(testBar.getId());

        assertThat(NAME_1, is(testBar2.getName()));

        restaurantService.evictCache();

        Restaurant testBar3 =  restaurantService.getById(testBar.getId());

        assertThat(NAME_2, is(testBar3.getName()));


    }
}
