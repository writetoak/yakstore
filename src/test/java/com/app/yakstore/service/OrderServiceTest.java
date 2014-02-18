package com.app.yakstore.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.app.yakstore.domain.Stock;
import com.app.yakstore.vo.ItemsEnum;
import com.app.yakstore.vo.OrderRequest;
import com.app.yakstore.vo.OrderResponse;

public class OrderServiceTest {
	
	private StockService mockStockService;
	private OrderService orderService;

	@Before
	public void setupUnitUnderTest() {
		mockStockService = mock(StockService.class);
		
		int nthDay = 5;
		
		Stock stock = new Stock();
		stock.addItem(ItemsEnum.MILK, 1000f);
		stock.addItem(ItemsEnum.SKINS, 3f);
		
		when(mockStockService.getStockDetailOnAnyDay(nthDay)).thenReturn(stock);
		
		orderService= new OrderService();
		orderService.setStockService(mockStockService);
		
	}

	public void testSubmitOrderWhenEnoughStockAvailable() {
		
		
		Map<String,Float> orderItems = new HashMap<String,Float>(2);
		orderItems.put(ItemsEnum.MILK.toString(), 100f);
		orderItems.put(ItemsEnum.SKINS.toString(), 2f);
		
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setOrder(orderItems);
		
		
		OrderResponse orderResponse = orderService.submitOrder(orderRequest);
		Map<String,Float> responseItems = orderResponse.getItems();
		System.out.println(responseItems);
	}
}
