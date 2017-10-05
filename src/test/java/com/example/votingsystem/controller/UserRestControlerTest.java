package com.example.votingsystem.controller;


import com.example.votingsystem.model.Roles;
import com.example.votingsystem.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.votingsystem.controller.UserRestControler.REST_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserRestControlerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void getAllTest() throws Exception {

		mockMvc.perform(get(REST_URL+"/users"))
                .andDo(print());

	}

	@Test
	public void getTest() throws Exception {

		mockMvc.perform(get(REST_URL+"/user/1"))
				.andDo(print());

	}

	@Test
	public void getByEmailTest() throws Exception {

		mockMvc.perform(get(REST_URL+"/userbyemail/user@gmail.com"))
				.andDo(print());
	}

	@Test
	public void updateTest() throws Exception {

		User updated = new User("newuser@ya.ru","123", Roles.USER);

		String result = objectMapper.writeValueAsString(updated);

		mockMvc.perform(post(REST_URL+"/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(result))
				.andDo(print());


	}

	@Test
	public void deleteTest() throws Exception {
		mockMvc.perform(delete(REST_URL+"/user/2"))
				.andDo(print());
	}

}
