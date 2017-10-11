package com.example.votingsystem.web;

import com.example.votingsystem.json.JsonUtil;
import com.example.votingsystem.model.Menu;
import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.to.MenuItemTo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.List;

import static com.example.votingsystem.web.AdminRestaurantRestControler.REST_URL;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Brad on 07.10.2017.
 */
public class AdminRestaurantRestControlerTest extends AbstractControllerTest {

    @Before
    public void setUp() {

        Restaurant testItem1 = new Restaurant("Пиворама", "Большевиков  проспект, 18 к2");
        Restaurant testItem2 = new Restaurant("Бахрома", "Наставников, проспек, 24 к1");

        restaurantService.save(testItem1);
        restaurantService.save(testItem2);

    }

    @Test
    public void testGet() throws Exception {

        Restaurant testItem = restaurantService.getAll().get(0);
        final String expected = JsonUtil.writeValue(testItem);

        String actual =
                mockMvc.perform(get(REST_URL + "/" + testItem.getId()))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        //.andDo(print())
                        .andReturn().getResponse().getContentAsString();

        assertThat(actual, containsString(expected));
    }

    @Test
    public void testGetByName() throws Exception {

        Restaurant testItem = restaurantService.getAll().get(0);
        String expected = JsonUtil.writeValue(testItem);

        String actual =
                mockMvc.perform(get(REST_URL + "/by").param("name", testItem.getName()))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        //.andDo(print())
                        .andReturn().getResponse().getContentAsString();

        assertThat(actual, containsString(expected));
    }

    @Test
    public void testGetAll() throws Exception {

        List<Restaurant> items1 = restaurantService.getAll();

        String actual =
                mockMvc.perform(get(REST_URL))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        //.andDo(print())
                        .andReturn().getResponse().getContentAsString();

        List<Restaurant> items2 = JsonUtil.readValues(actual, Restaurant.class);

        assertTrue(items1.size() == items2.size());

    }

    @Test
    public void testUpdate() throws Exception {

        Restaurant newItem = new Restaurant("Bear Bar", "ул. Комсомола д. 45");

        int count1 = restaurantService.getAll().size();

        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newItem))
        )
                //.andDo(print())
                .andExpect(status().isCreated());

        int count2 = restaurantService.getAll().size();

        assertTrue(count2 - count1 == 1);

    }

    @Test
    public void testDelete() throws Exception {

        Restaurant testItem = restaurantService.getAll().get(0);

        int count1 = restaurantService.getAll().size();

        mockMvc.perform(delete(REST_URL + "/" + testItem.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(status().isCreated());

        int count2 = restaurantService.getAll().size();

        assertTrue(count1 - count2 == 1);

    }

    @Test
    public void testUpdateBarsMenuItem() throws Exception {

        Restaurant testBar = restaurantService.getAll().get(0);
        MenuItemTo itemTo = new MenuItemTo("Уха царская", BigDecimal.valueOf(150.70), null);


        String response =
        mockMvc.perform(post(REST_URL + "/"+testBar.getId()+"/meals")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JsonUtil.writeValue(itemTo)))
                //.andDo(print())
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Menu testMenu = JsonUtil.readValue(response,Menu.class);

        assertThat("Уха царская",containsString(testMenu.getMeal().getName()));
        assertThat(BigDecimal.valueOf(150.70), comparesEqualTo(testMenu.getPrice()));

    }

    @Test
    public void testDeleteMenuItems()  throws Exception {
        //TODO
    }

    @Test
    public void testDeleteMenuItem()  throws Exception {
        //TODO
    }
}
