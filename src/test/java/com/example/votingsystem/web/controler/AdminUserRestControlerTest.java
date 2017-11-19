package com.example.votingsystem.web.controler;

import com.example.votingsystem.model.Roles;
import com.example.votingsystem.model.User;
import com.example.votingsystem.util.exception.ErrorType;
import com.example.votingsystem.web.controler.accessory.AbstractControllerTest;
import com.example.votingsystem.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static com.example.votingsystem.TestData.*;
import static com.example.votingsystem.TestUtil.userHttpBasic;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminUserRestControlerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminUserRestControler.REST_URL;//rest/admin/users

    @Test
    public void createTest() throws Exception {

        final int before = userService.getAll().size();

        String content = JsonUtil.writeValue(NEW_USER_1);

        mockMvc.perform(post(REST_URL + "/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .with(userHttpBasic(ADMIN))
        )
                .andDo(print())
                .andExpect(status().isCreated());

        final List<User> after = userService.getAll();

        assertThat(after, hasSize(equalTo(before + 1)));
    }

    @Test
    public void updateTest() throws Exception {

        final User updated = NEW_USER_3;

        userService.save(updated);

        updated.setPassword("user333");
        updated.setRole(Roles.ADMIN);

        final String content = JsonUtil.writeValue(updated);

        mockMvc.perform(put(REST_URL + "/"+updated.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .with(userHttpBasic(ADMIN))
        )
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(updated, is(userService.getById(updated.getId())));
    }


    @Test
    public void createWithExceptionTest() throws Exception {

        mockMvc.perform(post(REST_URL + "/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_USER_EMAIL_DUBLICATE))
                .with(userHttpBasic(ADMIN))
        )
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(errorType(ErrorType.DATA_ERROR))
        ;


        mockMvc.perform(post(REST_URL + "/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_USER_SMALL_PASW))
                .with(userHttpBasic(ADMIN))
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR));

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

        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentListMatcher(inspected));
    }

    @Test
    public void getTest() throws Exception {

        mockMvc.perform(get(REST_URL + "/" + USER.getId())
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentMatcher(USER));
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
