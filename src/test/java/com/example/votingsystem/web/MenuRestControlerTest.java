package com.example.votingsystem.web;

import com.example.votingsystem.json.JsonUtil;
import com.example.votingsystem.model.Menu;
import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.to.MenuItemTo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;


import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.votingsystem.web.MenuRestControler.REST_URL;

/**
 * Created by Brad on 12.10.2017.
 */
public class MenuRestControlerTest extends AbstractControllerTest {

    private static final Restaurant TEST_BAR_1 = new Restaurant("Пиворама", "Большевиков  проспект, 18 к2");

    @Before
    public void beforeTest() {

        restaurantService.save(TEST_BAR_1);

        List<MenuItemTo> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            items.add(new MenuItemTo("Еда" + i, BigDecimal.valueOf(200 + 5 * i), null));
            menuService.save(TEST_BAR_1.getId(), items.get(i));
        }
    }

    @Test
    public void getAllTest() throws Exception {

        String response =
        mockMvc.perform(get(REST_URL + "/" + TEST_BAR_1.getId() + "/meals")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Menu> menuList = JsonUtil.readValues(response, Menu.class);

        assertThat(10, is(menuList.size()));

    }

    @Test
    public void getAllByTest() throws Exception {

        String response =
        mockMvc.perform(get(REST_URL + "/" + TEST_BAR_1.getId() + "/meals/by")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .param("name","Еда1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Menu> menuList = JsonUtil.readValues(response, Menu.class);

        assertThat(1, is(menuList.size()));
    }

}
