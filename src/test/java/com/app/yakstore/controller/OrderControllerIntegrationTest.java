package com.app.yakstore.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.app.yakstore.service.OrderService;
import com.app.yakstore.vo.ItemsEnum;
import com.app.yakstore.vo.OrderRequest;
import com.app.yakstore.vo.OrderResponse;

/*
 * Purpose of this class is to capture integration tests for the OrderController.
 */


public class OrderControllerIntegrationTest 
{
	MockMvc mockMvc;

	@InjectMocks
	OrderController controller;

	@Mock
	OrderService orderService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(controller).build();
	}
	
	
	@Test
	public void thatPostOrderReturnsHttpOK() throws Exception {
		OrderResponse orderResponse = new OrderResponse(); 
		orderResponse.addItem(ItemsEnum.MILK.toString(), 1000f);
		orderResponse.addItem(ItemsEnum.SKINS.toString(), 3f);
		
		when(orderService.submitOrder(any(OrderRequest.class))).thenReturn(orderResponse);
		
		String order = "{\"customer\" : \"Medvedev\", \"order\" : { \"milk\" : 100, \"skins\" : 1} }";
		
		ResultActions results = this.mockMvc.perform(post("/order/1").content(order).contentType(MediaType.APPLICATION_JSON));
		
		results.andExpect(status().isCreated());
		MockHttpServletResponse response = results.andReturn().getResponse();
		System.out.println(response);
	}


	
	@Test
	public void thatPostOrderReturnsHttpPartial() throws Exception {
		OrderResponse orderResponse = new OrderResponse(); 
		orderResponse.addItem(ItemsEnum.MILK.toString(), 1000f);
		
		when(orderService.submitOrder(any(OrderRequest.class))).thenReturn(orderResponse);
		
		String order = "{\"customer\" : \"Medvedev\", \"order\" : { \"milk\" : 1100, \"skins\" : 1} }";
		
		ResultActions results = this.mockMvc.perform(post("/order/1").content(order).contentType(MediaType.APPLICATION_JSON));
		
		results.andExpect(status().isPartialContent());
		MockHttpServletResponse response = results.andReturn().getResponse();
		System.out.println(response);
	}

	
	@Test
	public void thatPostOrderReturnsHttpNotFound() throws Exception {
		OrderResponse orderResponse = new OrderResponse(); 
		
		when(orderService.submitOrder(any(OrderRequest.class))).thenReturn(orderResponse);
		
		String order = "{\"customer\" : \"Medvedev\", \"order\" : { \"milk\" : 1100, \"skins\" : 4} }";
		
		ResultActions results = this.mockMvc.perform(post("/order/1").content(order).contentType(MediaType.APPLICATION_JSON));
		
		results.andExpect(status().isNotFound());
		MockHttpServletResponse response = results.andReturn().getResponse();
		System.out.println(response);
	}
	
	
	@Test
	public void thatGetOrderReturnsHttpBadRequest() throws Exception {
		OrderResponse orderResponse = new OrderResponse(); 
		orderResponse.addItem(ItemsEnum.MILK.toString(), 1000f);
		orderResponse.addItem(ItemsEnum.SKINS.toString(), 3f);
		
		when(orderService.submitOrder(any(OrderRequest.class))).thenReturn(orderResponse);
		ResultActions results = this.mockMvc.perform(get("/order/1"));
		results.andExpect(status().isMethodNotAllowed());
	}
}
