package com.app.yakstore.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.app.yakstore.domain.Stock;
import com.app.yakstore.domain.Yak;
import com.app.yakstore.service.StockService;
import com.app.yakstore.vo.YakDto;

/*
 * Purpose of this class is to capture integration tests for the StockController.
 */

public class StockControllerIntegrationTest {
	MockMvc mockMvc;

	@InjectMocks
	StockController controller;

	@Mock
	StockService stockService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(controller).build();
	}


	@Test
	public void thatGetStockUsesHttpOK() throws Exception {

		when(stockService.getStockDetailOnAnyDay(1)).thenReturn(new Stock());
		ResultActions results = this.mockMvc.perform(get("/stock/1").accept(MediaType.APPLICATION_JSON));
		results.andExpect(status().isOk());
		results.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		MockHttpServletResponse response = results.andReturn().getResponse();
		System.out.println(response);
	}


	@Test
	public void thatGetHerdUsesHttpOK() throws Exception {

		when(stockService.getHerdDetailOnAnyDay(1)).thenReturn(new ArrayList<YakDto>());
		ResultActions results = this.mockMvc.perform(get("/herd/1").accept(MediaType.APPLICATION_JSON));
		results.andExpect(status().isOk());
		results.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	
	
	@Test
	public void thatGetStockWrongUrlReturnsHttpBadRequest() throws Exception {

		when(stockService.getStockDetailOnAnyDay(1)).thenReturn(new Stock());
		ResultActions results = this.mockMvc.perform(get("/stock/xyz").accept(MediaType.APPLICATION_JSON));
		results.andExpect(status().isBadRequest());
		
	}


	@Test
	public void thatGetHerdWrongUrlReturnsHttpBadRequest() throws Exception {

		when(stockService.getHerdDetailOnAnyDay(1)).thenReturn(new ArrayList<YakDto>());
		ResultActions results = this.mockMvc.perform(get("/herd/xyz").accept(MediaType.APPLICATION_JSON));
		results.andExpect(status().isBadRequest());
		
	}
	
	
	

}
