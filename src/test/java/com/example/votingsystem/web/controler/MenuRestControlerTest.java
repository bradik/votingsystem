package com.example.votingsystem.web.controler;

import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.web.json.JsonUtil;
import com.example.votingsystem.model.Menu;
import com.example.votingsystem.web.to.MenuItemTo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.example.votingsystem.TestUtil.userHttpBasic;
import static com.example.votingsystem.TestData.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Brad on 12.10.2017.
 */
public class MenuRestControlerTest extends AbstractControllerTest {

    private static final String REST_URL = MenuRestControler.REST_URL;//"/rest/user/bars"

    @Before
    public void beforeTest() {

        restaurantService.save(TEST_BAR_1);

        List<MenuItemTo> items = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            items.add(new MenuItemTo("Еда" + i, BigDecimal.valueOf(200 + 5 * i), null));
            menuService.save(TEST_BAR_1.getId(), items.get(i));
        }
    }

    @Test
    public void getAllTest() throws Exception {

        String response =
                mockMvc.perform(get(REST_URL + "/" + TEST_BAR_1.getId() + "/meals")
                        .with(userHttpBasic(USER))
                )
                        //.andDo(print())
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

        String response1 =
                mockMvc.perform(get(REST_URL + "/" + TEST_BAR_1.getId() + "/meals")
                        .with(userHttpBasic(USER))
                )
                        //.andDo(print())
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

        List<Menu> menuList = JsonUtil.readValues(response, Menu.class);

        assertThat(5, is(menuList.size()));

    }

    @Test
    public void getAllByTest() throws Exception {

        String response =
                mockMvc.perform(get(REST_URL + "/" + TEST_BAR_1.getId() + "/meals/by")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .param("name", "Еда1")
                        .with(userHttpBasic(ADMIN))
                )
                        //.andDo(print())
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

        List<Menu> menuList = JsonUtil.readValues(response, Menu.class);

        assertThat(1, is(menuList.size()));
    }

}
