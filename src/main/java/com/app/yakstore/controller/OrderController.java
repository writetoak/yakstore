package com.app.yakstore.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.yakstore.service.OrderService;
import com.app.yakstore.vo.OrderRequest;
import com.app.yakstore.vo.OrderResponse;

/**
 * Handles requests for the application home page.
 */
@RestController
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/order/{orderDay}", method = RequestMethod.POST)
	public @ResponseBody OrderResponse submitOrders(@RequestBody OrderRequest orderRequest, HttpServletResponse response, @PathVariable("orderDay") int orderDay) {
		
		logger.debug(orderRequest.toString());
		
		if(orderRequest ==null || orderRequest.getItems() ==null || orderRequest.getItems().isEmpty())
		{
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return null;
		}
		
		orderRequest.setOrderDay(orderDay);
		OrderResponse orderResponse = orderService.submitOrder(orderRequest);
		
		int requestedItems = orderRequest.getItems() !=null ? orderRequest.getItems().size() : 0;
		int responseItems = orderResponse.getItems() !=null ? orderResponse.getItems().size() : 0;
		
		
		if(responseItems ==0)
		{
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
		else if(responseItems == requestedItems)
		{
			response.setStatus(HttpStatus.CREATED.value());
		}
		else if(responseItems < requestedItems)
		{
			response.setStatus(HttpStatus.PARTIAL_CONTENT.value());
		}
		
		return orderResponse;
	}
	
}
