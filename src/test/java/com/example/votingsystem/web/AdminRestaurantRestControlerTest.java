package com.example.votingsystem.web;

import com.example.votingsystem.json.JsonUtil;
import com.example.votingsystem.model.Restaurant;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static com.example.votingsystem.TestUtil.userHttpBasic;
import static com.example.votingsystem.TestData.*;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Brad on 07.10.2017.
 */
public class AdminRestaurantRestControlerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestaurantRestControler.REST_URL;

    @Before
    public void beforeTest() {
        restaurantService.save(TEST_BAR_1);
        restaurantService.save(TEST_BAR_2);
    }

    @Test
    public void getTest() throws Exception {

        Restaurant testItem = restaurantService.getAll().get(0);
        final String expected = JsonUtil.writeValue(testItem);

        String actual =
                mockMvc.perform(get(REST_URL + "/" + testItem.getId())
                        .with(userHttpBasic(ADMIN))
                )
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        //.andDo(print())
                        .andReturn().getResponse().getContentAsString();

        assertThat(actual, containsString(expected));
    }

    @Test
    public void getByNameTest() throws Exception {

        Restaurant testItem = restaurantService.getAll().get(0);
        String expected = JsonUtil.writeValue(testItem);

        //TODO: добавить уникальность имени, починить тест!!!

        String actual =
                mockMvc.perform(get(REST_URL + "/by").param("name", testItem.getName())
                        .with(userHttpBasic(ADMIN))
                )
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        //.andDo(print())
                        .andReturn().getResponse().getContentAsString();

        assertThat(actual, containsString(expected));
    }

    @Test
    public void getAllTest() throws Exception {

        List<Restaurant> items1 = restaurantService.getAll();

        String actual =
                mockMvc.perform(get(REST_URL)
                        .with(userHttpBasic(ADMIN))
                )
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        //.andDo(print())
                        .andReturn().getResponse().getContentAsString();

        List<Restaurant> items2 = JsonUtil.readValues(actual, Restaurant.class);

        assertTrue(items1.size() == items2.size());

    }

    @Test
    public void updateTest() throws Exception {

        Restaurant newItem = new Restaurant("Bear Bar", "ул. Комсомола д. 45");

        int count1 = restaurantService.getAll().size();

        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newItem))
                .with(userHttpBasic(ADMIN))
        )
                //.andDo(print())
                .andExpect(status().isCreated());

        int count2 = restaurantService.getAll().size();

        assertTrue(count2 - count1 == 1);

    }

    @Test
    public void deleteTest() throws Exception {

        Restaurant testItem = restaurantService.getAll().get(0);

        int count1 = restaurantService.getAll().size();

        mockMvc.perform(delete(REST_URL + "/" + testItem.getId())
                .with(userHttpBasic(ADMIN))
        )
                //.andDo(print())
                .andExpect(status().isOk());

        int count2 = restaurantService.getAll().size();

        assertTrue(count1 - count2 == 1);

    }

}
