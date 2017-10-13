package com.example.votingsystem.web;

import com.example.votingsystem.TestUtil;
import com.example.votingsystem.json.JsonUtil;
import com.example.votingsystem.model.Roles;
import com.example.votingsystem.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static com.example.votingsystem.TestUtil.userHttpBasic;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminUserRestControlerTest extends AbstractControllerTest {

    static final String REST_URL = AdminUserRestControler.REST_URL;

    @Test
    public void testGetAll() throws Exception {

        User ADMIN = new User(1, "admin@gmail.com", "admin", Roles.ROLE_ADMIN);

        List<User> items1 = userService.getAll();

        String actual =
                mockMvc.perform(get(REST_URL)
                        .with(userHttpBasic(ADMIN))
                )
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString();

        List<User> items2 = JsonUtil.readValues(actual, User.class);

        Assert.assertTrue(items1.size() == items2.size());

    }

    @Test
    public void getTest() throws Exception {

        User testItem = userService.getById(1);
        final String expected = JsonUtil.writeValue(testItem);

        String actual =
                mockMvc.perform(get(REST_URL + "/1"))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString();

        Assert.assertThat(actual, containsString(expected));
    }

    @Test
    public void testGetByEmail() throws Exception {

        User testItem = userService.getById(1);
        String expected = JsonUtil.writeValue(testItem);

        String actual =
                mockMvc.perform(get(REST_URL + "/by").param("email", testItem.getEmail()))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString();

        Assert.assertThat(actual, containsString(expected));

    }


    @Test
    public void testCreate() throws Exception {

        User newItem = new User("newuser@ya.ru", "123", Roles.ROLE_USER);

        int count1 = userService.getAll().size();

        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newItem))
        )
                .andExpect(status().isCreated())
                .andDo(print());

        int count2 = userService.getAll().size();

        Assert.assertTrue(count2 - count1 == 1);

    }

    @Test
    public void testDelete() throws Exception {

        int count1 = userService.getAll().size();

        mockMvc.perform(delete(REST_URL + "/1"))
                .andDo(print());

        int count2 = userService.getAll().size();

        Assert.assertTrue(count1 - count2 == 1);

    }


}
