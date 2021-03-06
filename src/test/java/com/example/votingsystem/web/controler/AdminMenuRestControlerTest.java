package com.example.votingsystem.web.controler;

import com.example.votingsystem.TestData;
import com.example.votingsystem.model.Menu;
import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.util.DateTimeUtil;
import com.example.votingsystem.web.controler.accessory.AbstractControllerTest;
import com.example.votingsystem.web.json.JsonUtil;
import com.example.votingsystem.web.to.MenuItemTo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.List;

import static com.example.votingsystem.TestData.ADMIN;
import static com.example.votingsystem.TestData.YESTERDAY;
import static com.example.votingsystem.TestUtil.userHttpBasic;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminMenuRestControlerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminMenuRestControler.REST_URL;//rest/admin/bars

    private static final Restaurant TEST_BAR_1 = TestData.TEST_BAR_3;
    private static final Restaurant TEST_BAR_2 = TestData.TEST_BAR_4;

    @Before
    public void beforeTest() {

        restaurantService.save(TEST_BAR_1);

        for (int i = 0; i < 2; i++) {

            MenuItemTo menuItem = new MenuItemTo("Еда" + (i+1), BigDecimal.valueOf(200 + 5 * i), null);

            menuService.save(TEST_BAR_1.getId(), menuItem);
        }

        restaurantService.save(TEST_BAR_2);

        for (int i = 0; i < 2; i++) {

            MenuItemTo menuItem = new MenuItemTo("Еда" + (i+1), BigDecimal.valueOf(200 + 5 * i), YESTERDAY);

            menuService.save(TEST_BAR_2.getId(), menuItem);
        }

    }


    @Test
    public void updateBarsMenuItemTest() throws Exception {

        MenuItemTo itemTo = new MenuItemTo("Уха царская", BigDecimal.valueOf(150.70), null);
        String content = JsonUtil.writeValue(itemTo);
        String response =
                mockMvc.perform(put(REST_URL + "/" + TEST_BAR_1.getId() + "/meals")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(content)
                        .with(userHttpBasic(ADMIN))
                )
                        .andDo(print())
                        .andExpect(status().isCreated())
                        .andReturn().getResponse().getContentAsString();

        Menu testMenu = JsonUtil.readValue(response, Menu.class);

        assertThat("Уха царская", is(testMenu.getMeal().getName()));
        assertThat(BigDecimal.valueOf(150.70), comparesEqualTo(testMenu.getPrice()));

    }

    @Test
    public void deleteMenuItemsTest() throws Exception {

        List<Menu> list1 =  menuService.getAll(TEST_BAR_1.getId());

        mockMvc.perform(delete(REST_URL + "/" + TEST_BAR_1.getId() + "/meals")
                .with(userHttpBasic(ADMIN))
        )
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());

        List<Menu> list2 =  menuService.getAll(TEST_BAR_1.getId());

        final int actualSize = list1.size() - list2.size();
        assertThat(actualSize, is(2));

    }

    @Test
    public void deleteMenuItemsByNameOnDateTest() throws Exception {

        List<Menu> list1 =  menuService.findBy(TEST_BAR_2.getId(), YESTERDAY, null, null);

        mockMvc.perform(delete(REST_URL + "/" + TEST_BAR_2.getId() + "/meals/by")
                .param("name", "Еда2")
                .param("date", DateTimeUtil.of(YESTERDAY))
                .with(userHttpBasic(ADMIN))
        )
                .andDo(print())
                .andExpect(status().isOk());

        List<Menu> list2 = menuService.findBy(TEST_BAR_2.getId(), YESTERDAY, null, null);

        final int actualSize = list1.size() - list2.size();
        assertThat(actualSize, is(1));
    }

}
