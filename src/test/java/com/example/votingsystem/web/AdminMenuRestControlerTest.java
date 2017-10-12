package com.example.votingsystem.web;

import com.example.votingsystem.json.JsonUtil;
import com.example.votingsystem.model.Menu;
import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.to.MenuItemTo;
import com.example.votingsystem.util.DateTimeUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static com.example.votingsystem.web.AdminMenuRestControler.REST_URL;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminMenuRestControlerTest extends AbstractControllerTest {

    private static final Restaurant TEST_BAR_1 = new Restaurant("Пиворама", "Большевиков  проспект, 18 к2");
    private static final Restaurant TEST_BAR_2 = new Restaurant("Бахрома", "Наставников, проспек, 24 к1");
    private static final LocalDate YESTERDAY = LocalDate.now().minusDays(1);

    @Before
    public void beforeTest() {

        restaurantService.save(TEST_BAR_1);
        restaurantService.save(TEST_BAR_2);

        MenuItemTo menuItem1 = new MenuItemTo("Еда1", BigDecimal.valueOf(200), null);
        MenuItemTo menuItem2 = new MenuItemTo("Еда2", BigDecimal.valueOf(250), null);

        menuService.save(TEST_BAR_1.getId(), menuItem1);
        menuService.save(TEST_BAR_1.getId(), menuItem2);

        MenuItemTo menuItem21 = new MenuItemTo("Еда1", BigDecimal.valueOf(200), YESTERDAY);
        MenuItemTo menuItem22 = new MenuItemTo("Еда2", BigDecimal.valueOf(250), YESTERDAY);

        menuService.save(TEST_BAR_2.getId(), menuItem21);
        menuService.save(TEST_BAR_2.getId(), menuItem22);

    }

    @After
    public void afterTest() {

        List<Menu> menuList = menuService.findBy(TEST_BAR_1.getId(), null, null, null);
        for (Menu menu : menuList) {
            menuService.delete(menu.getId());
        }

        menuList = menuService.findBy(TEST_BAR_2.getId(), YESTERDAY, null, null);
        for (Menu menu : menuList) {
            menuService.delete(menu.getId());
        }

        restaurantService.delete(TEST_BAR_1.getId());
        restaurantService.delete(TEST_BAR_2.getId());

    }

    @Test
    public void testUpdateBarsMenuItem() throws Exception {

        MenuItemTo itemTo = new MenuItemTo("Уха царская", BigDecimal.valueOf(150.70), null);


        String response =
                mockMvc.perform(post(REST_URL + "/" + TEST_BAR_1.getId() + "/meals")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(JsonUtil.writeValue(itemTo)))
                        //.andDo(print())
                        .andExpect(status().isCreated())
                        .andReturn().getResponse().getContentAsString();

        Menu testMenu = JsonUtil.readValue(response, Menu.class);

        assertThat("Уха царская", containsString(testMenu.getMeal().getName()));
        assertThat(BigDecimal.valueOf(150.70), comparesEqualTo(testMenu.getPrice()));

    }

    @Test
    public void testDeleteMenuItems() throws Exception {

        int size1 = menuService.getAll(TEST_BAR_1.getId()).size();

        mockMvc.perform(delete(REST_URL + "/" + TEST_BAR_1.getId() + "/meals"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());

        int size2 = menuService.getAll(TEST_BAR_1.getId()).size();

        assertThat(size1 - size2, is(2));

    }

    @Test
    public void testDeleteMenuItem() throws Exception {

        int size1 = menuService.findBy(TEST_BAR_2.getId(), YESTERDAY, null, null).size();

        mockMvc.perform(delete(REST_URL + "/" + TEST_BAR_2.getId() + "/meals/by")
                .param("name", "Еда2")
                .param("date", DateTimeUtil.of(YESTERDAY)))
                .andDo(print())
                .andExpect(status().isOk());

        int size2 = menuService.findBy(TEST_BAR_2.getId(), YESTERDAY, null, null).size();

        assertThat(size1 - size2, is(1));
    }

}
