package com.app.yakstore.service;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.yakstore.domain.Stock;
import com.app.yakstore.vo.OrderRequest;
import com.app.yakstore.vo.OrderResponse;

@Service
public class OrderService 
{
	
	@Autowired
	private StockService stockService;
	
	
	public OrderResponse submitOrder(OrderRequest orderRequest)
	{
		OrderResponse orderResponse = new OrderResponse();
		
		if(orderRequest!=null)
		{
			Stock currentStock = stockService.getStockDetailOnAnyDay(orderRequest.getOrderDay());
			Map<String,Float> currentStockItems = currentStock.getItems();
			Map<String,Float> requestedOrderItems = orderRequest.getItems();

			Iterator<Entry<String,Float>> orderItemItr = requestedOrderItems.entrySet().iterator();
			
			Entry<String,Float> currentEntry = null;
			
			while(orderItemItr.hasNext())
			{
				currentEntry = orderItemItr.next();
				
				if(currentStockItems.containsKey(currentEntry.getKey().toUpperCase()))
				{
					if(currentStockItems.get(currentEntry.getKey().toUpperCase()).floatValue() >= currentEntry.getValue().floatValue())
					{
						orderResponse.addItem(currentEntry.getKey(), currentEntry.getValue());
					}
				}
			}
			
			
		}
		
		return orderResponse;
	}


	public StockService getStockService() {
		return stockService;
	}


	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}
	
	
	
	
}
