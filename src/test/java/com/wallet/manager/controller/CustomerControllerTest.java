package com.wallet.manager.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.wallet.manager.controller.constant.Constants;


@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest extends ControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private CustomerController controller;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	@Test
	void testGetRegisteredCustomers_Success() throws Exception {		
		this.mockMvc.perform(get(Constants.API_CUSTOMERS)).andDo(print()).andExpect(status().isOk());
	}
	

	
}
