package com.app.yakstore.vo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OrderResponse 
{
	private Map<String,Float> items = new HashMap<String, Float>(2);

	public Map<String, Float> getItems() {
		return Collections.unmodifiableMap(items);
	}

	public void addItem(String description,float quantity)
	{
		items.put(description,quantity);
	}

	@Override
	public String toString() {
		return "OrderResponse [items=" + items + "]";
	}
	
	
}
