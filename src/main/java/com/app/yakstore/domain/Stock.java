package com.app.yakstore.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.app.yakstore.vo.ItemsEnum;

public class Stock 
{
	
	private Map<String,Float> items;

	public Stock()
	{
		items = new HashMap<String,Float>(2); 
	}
	
	
	public void addItem(ItemsEnum description, float quantity){
		items.put(description.toString(),quantity);
	}

	
	public Float getQuanity(ItemsEnum description)
	{
		return description !=null ? items.get(description.toString()) : 0f ;
	}
	
	
	public Map<String, Float> getItems() {
		return Collections.unmodifiableMap(items);
	}


	@Override
	public String toString() {
		return "Stock [items=" + items + "]";
	}



	
}


/*public Stock(Milk milk, int noOfSkins) {
super();
this.milk = milk;
this.noOfSkins = noOfSkins;
}*/


/*public Milk getMilk() {
return milk;
}


public Stock(float milk, int noOfSkins) {
		super();
		this.noOfSkins = noOfSkins;
		this.milk = milk;
	}
*/

/*public int getNoOfSkins() {
return noOfSkins;
}


public float getMilk() {
return milk;
}
*/

/*	public void setMilk(byte milk) {
this.milk = milk;
}
*/

/*	private int noOfSkins;
private float milk;*/

