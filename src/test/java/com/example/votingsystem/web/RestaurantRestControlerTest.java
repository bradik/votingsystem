package com.example.votingsystem.web;

import com.example.votingsystem.json.JsonUtil;
import com.example.votingsystem.model.Restaurant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static com.example.votingsystem.web.RestaurantRestControler.REST_URL;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Brad on 07.10.2017.
 */
public class RestaurantRestControlerTest extends AbstractControllerTest {

    @Before
    public void setUp() {

        Restaurant testItem1 = new Restaurant("Пиворама", "Большевиков  проспект, 18 к2");
        Restaurant testItem2 = new Restaurant("Бахрома", "Наставников, проспек, 24 к1");

        restaurantService.save(testItem1);
        restaurantService.save(testItem2);

    }

    @Test
    public void getTest() throws Exception {

        Restaurant testItem = restaurantService.getAll().get(0);
        final String expected = JsonUtil.writeValue(testItem);

        String actual =
                mockMvc.perform(get(REST_URL + "/" + testItem.getId()))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString();

        Assert.assertThat(actual, containsString(expected));
    }

    @Test
    public void getByNameTest() throws Exception {

        Restaurant testItem = restaurantService.getAll().get(0);
        String expected = JsonUtil.writeValue(testItem);

        String actual =
                mockMvc.perform(get(REST_URL + "/by").param("name", testItem.getName()))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString();

        Assert.assertThat(actual, containsString(expected));
    }

    @Test
    public void getAllTest() throws Exception {

        List<Restaurant> items1 = restaurantService.getAll();

        String actual =
                mockMvc.perform(get(REST_URL))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString();

        List<Restaurant> items2 = JsonUtil.readValues(actual, Restaurant.class);

        Assert.assertTrue(items1.size() == items2.size());

    }

    @Test
    public void updateTest() throws Exception {

        Restaurant newItem = new Restaurant("Bear Bar", "ул. Комсомола д. 45");

        int count1 = restaurantService.getAll().size();

        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newItem))
        )
                .andExpect(status().isCreated())
                .andDo(print());

        int count2 = restaurantService.getAll().size();

        Assert.assertTrue(count2 - count1 == 1);

    }

    @Test
    public void deleteTest() throws Exception {

        Restaurant testItem = restaurantService.getAll().get(0);

        int count1 = restaurantService.getAll().size();

        mockMvc.perform(delete(REST_URL + "/" + testItem.getId()))
                .andDo(print());

        int count2 = restaurantService.getAll().size();

        Assert.assertTrue(count1 - count2 == 1);

    }

    @Test
    public void updateBarsMenuItemTest() throws Exception {

        Restaurant testItem = restaurantService.getAll().get(0);

        mockMvc.perform(post(REST_URL + "/"+testItem.getId()+"/meals").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print());


    }
}
