package com.wallet.manager.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.wallet.manager.controller.constant.Constants;
import com.wallet.manager.dto.TransferDTO;
import com.wallet.manager.model.Customer;
import com.wallet.manager.model.Wallet;

@SpringBootTest
@AutoConfigureMockMvc
public class WalletControllerTest extends ControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WalletController controller;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	@Transactional
	@Test
    void testGetCustomerWallets_Success() throws Exception {
		Customer customer = createTestSingleCustomer();
		createTestWalletOfCustomer(customer, "Wallet 1", new BigDecimal("10.00"));	
		this.mockMvc.perform(get(Constants.API_WALLETS + "?email="+customer.getEmail())).andDo(print()).andExpect(status().isOk());
	}
	
	@Transactional
	@Test
    void testTransferFunds_Success() throws Exception {
		List<Customer> customers = createTestCustomerList();
		Wallet wallet1 = createTestWalletOfCustomer(customers.get(0), "Wallet 1", new BigDecimal("20.00"));	
		Wallet wallet2 = createTestWalletOfCustomer(customers.get(0), "Wallet 2", new BigDecimal("5.00"));	

		TransferDTO transferDTO = new TransferDTO();
		transferDTO.setSenderHash(wallet1.getHash());
		transferDTO.setRecipientHash(wallet2.getHash());
		transferDTO.setFunds(new BigDecimal("10.00"));
		this.mockMvc.perform(put(Constants.API_WALLETS + Constants.API_WALLETS_TRANSFER)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(transferDTO))).andExpect(status().isOk());
	}

}
