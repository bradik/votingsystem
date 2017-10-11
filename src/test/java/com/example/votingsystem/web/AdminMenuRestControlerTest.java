package com.example.votingsystem.web;

import com.example.votingsystem.json.JsonUtil;
import com.example.votingsystem.model.Meal;
import com.example.votingsystem.model.Menu;
import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.to.MenuItemTo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static com.example.votingsystem.web.AdminMenuRestControler.REST_URL;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminMenuRestControlerTest extends AbstractControllerTest {

    @Before
    public void setUp() {

        Restaurant testBar1 = new Restaurant("Пиворама", "Большевиков  проспект, 18 к2");
        Restaurant testBar2 = new Restaurant("Бахрома", "Наставников, проспек, 24 к1");

        restaurantService.save(testBar1);
        restaurantService.save(testBar2);

        Menu menuItem1 = new Menu(testBar1,new Meal("Еда1"),BigDecimal.valueOf(200));
        Menu menuItem2 = new Menu(testBar1,new Meal("Еда2"),BigDecimal.valueOf(300));
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
        Restaurant testBar = restaurantService.getAll().get(0);
        mockMvc.perform(delete(REST_URL + "/"+testBar.getId()+"/meals"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void testDeleteMenuItem()  throws Exception {
        //TODO
    }

}
