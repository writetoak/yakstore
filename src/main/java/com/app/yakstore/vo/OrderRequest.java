package com.app.yakstore.vo;

import java.util.Map;

public class OrderRequest 
{
	private String customer;
	private Map<String,Float> order;
	private int orderDay;
	
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public Map<String, Float> getItems() {
		return order;
	}
	public void setOrder(Map<String, Float> order) {
		this.order = order;
	}
	
	public int getOrderDay() {
		return orderDay;
	}
	public void setOrderDay(int orderDay) {
		this.orderDay = orderDay;
	}
	
	@Override
	public String toString() {
		return "OrderRequest [customer=" + customer + ", items=" + order
				+ ", orderDay=" + orderDay + "]";
	}
	
}
