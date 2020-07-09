package com.employee.portal.controller;


import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.ModelAndView;

@SpringBootTest 
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EmployeePortalController.class)
class EmployeePortalControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void hello() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/login"))
		.andExpect(status().isOk())
		.andExpect(view().name("login"))
		.andExpect(model().attribute("msg", equalTo("welcome")))
		.andExpect(content().string(containsString("Hello, welcome")));
		MvcResult mvcResult = resultActions.andReturn();
        ModelAndView mv = mvcResult.getModelAndView();
	}
}
