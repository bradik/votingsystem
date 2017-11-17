package com.example.votingsystem.web.controler;

import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.web.controler.accessory.AbstractControllerTest;
import com.example.votingsystem.web.json.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static com.example.votingsystem.TestData.*;
import static com.example.votingsystem.TestUtil.userHttpBasic;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
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
    public void getAllTest() throws Exception {

        List<Restaurant> items1 = restaurantService.getAll();

        String actual =
                mockMvc.perform(get(REST_URL)
                        .with(userHttpBasic(ADMIN))
                )
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString();

        List<Restaurant> items2 = JsonUtil.readValues(actual, Restaurant.class);


        assertTrue(items1.size() == items2.size());

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
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString();

        assertThat(actual, containsString(expected));
    }

    @Test
    public void getByNameTest() throws Exception {

        Restaurant testItem = restaurantService.getAll().get(0);
        String expected = JsonUtil.writeValue(testItem);

        String actual =
                mockMvc.perform(get(REST_URL + "/by").param("name", testItem.getName())
                        .with(userHttpBasic(ADMIN))
                )
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString();

        assertThat(actual, containsString(expected));
    }

    @Test
    public void createTest() throws Exception {

        final Restaurant newItem = TEST_BAR_NEW;

        String response =
                mockMvc.perform(post(REST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.writeValue(newItem))
                        .with(userHttpBasic(ADMIN))
                )
                        .andDo(print())
                        .andExpect(status().isCreated())
                        .andReturn().getResponse().getContentAsString();

        Restaurant actual = JsonUtil.readValue(response, Restaurant.class);
        newItem.setId(actual.getId());
        BAR_MATCHER.assertEquals(newItem, actual);
    }

    @Test
    public void updateTest() throws Exception {

        final String newName = "updated";

        Restaurant updated = TEST_BAR_UPDATE;
        restaurantService.save(updated);

        updated.setName(newName);

        mockMvc.perform(put(REST_URL + "/" + updated.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN))
        )
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(updated, is(restaurantService.getById(updated.getId())));
    }

    @Test
    public void deleteTest() throws Exception {

        final Restaurant deleted = TEST_BAR_DELETE;

        restaurantService.save(deleted);

        final int before = restaurantService.getAll().size();

        mockMvc.perform(delete(REST_URL + "/" + deleted.getId())
                .with(userHttpBasic(ADMIN))
        )
                .andDo(print())
                .andExpect(status().isOk());

        final int after = restaurantService.getAll().size();

        assertThat(after, equalTo(before - 1));

    }

}
