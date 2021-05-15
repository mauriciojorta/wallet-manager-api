package com.wallet.manager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.manager.repository.RepositoryTest;

public class ControllerTest extends RepositoryTest {
	
    protected String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
