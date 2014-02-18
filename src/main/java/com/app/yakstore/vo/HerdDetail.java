package com.app.yakstore.vo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class HerdDetail 
{
	private List<HashMap<String,String>> herd;

	public HerdDetail()
	{
		herd = new LinkedList<HashMap<String,String>>();
	}
	

	public List<HashMap<String, String>> getHerd() {
		return herd;
	}


	@Override
	public String toString() {
		return "HerdDetail [herd=" + herd + "]";
	}
	
	public void addYaks(List<YakDto> yaks)
	{
		HashMap<String,String> yakDetails = null;
		
		for(YakDto yak:yaks)
		{
			yakDetails = new HashMap<String, String>(3);
			yakDetails.put("name",yak.getName());
			yakDetails.put("age",""+yak.getAge());
			yakDetails.put("age-last-shaved",""+yak.getAgeLastShaved());
			herd.add(yakDetails);
		}
		
		
	}
	
	
	
	
}
