package com.example.votingsystem.web.controler;

import com.example.votingsystem.model.User;
import com.example.votingsystem.util.exception.ErrorType;
import com.example.votingsystem.web.controler.accessory.AbstractControllerTest;
import com.example.votingsystem.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static com.example.votingsystem.TestData.*;
import static com.example.votingsystem.TestUtil.userHttpBasic;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminUserRestControlerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminUserRestControler.REST_URL;//rest/admin/users


    @Test
    public void getTest() throws Exception {

        String actual =
                mockMvc.perform(get(REST_URL + "/" + ADMIN.getId())
                        .with(userHttpBasic(ADMIN)))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse().getContentAsString();

        User actualUser = JsonUtil.readValue(actual, User.class);

        USER_MATCHER.assertEquals(ADMIN, actualUser);
    }

    @Test
    public void createTest() throws Exception {

        final int before = userService.getAll().size();

        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_USER_1))
                .with(userHttpBasic(ADMIN))
        )
                .andDo(print())
                .andExpect(status().isCreated());

        final List<User> after = userService.getAll();

        assertThat(after, hasSize(equalTo(before + 1)));

    }


    @Test
    public void createDuplicateEmailExceptionTest() throws Exception {

        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_USER_EMAIL_DUBLICATE))
                .with(userHttpBasic(ADMIN))
        )
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(errorType(ErrorType.DATA_ERROR))
        ;

    }


    @Test
    public void deleteTest() throws Exception {

        userService.save(NEW_USER_2);

        final int before = userService.getAll().size();

        mockMvc.perform(delete(REST_URL + "/" + NEW_USER_2.getId())
                .with(userHttpBasic(ADMIN))
        )
                .andDo(print())
                .andExpect(status().isOk());

        final List<User> after = userService.getAll();

        assertThat(after, hasSize(equalTo(before - 1)));

    }

    @Test
    public void getAllTest() throws Exception {

        List<User> inspected = userService.getAll();
        //inspected.add(NEW_USER_2);

        mockMvc.perform(get(REST_URL).with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentListMatcher(inspected));
    }

    @Test
    public void getByEmailTest() throws Exception {

        mockMvc.perform(get(REST_URL + "/by")
                .param("email", USER.getEmail()).with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentMatcher(USER));

    }


}
