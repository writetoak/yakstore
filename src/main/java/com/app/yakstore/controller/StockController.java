package com.app.yakstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.yakstore.service.StockService;
import com.app.yakstore.vo.HerdDetail;
import com.app.yakstore.vo.YakDto;

@RestController
public class StockController 
{
	
	@Autowired
	private StockService stockService;
	

	@RequestMapping(value="/stock/{nthDay}",method = RequestMethod.GET, produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String,Float> getStock(@PathVariable("nthDay") int nthDay) {
		return stockService.getStockDetailOnAnyDay(nthDay).getItems();
	}
	
	
	
	@RequestMapping(value="/herd/{nthDay}",method = RequestMethod.GET, produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody HerdDetail getHerd(@PathVariable("nthDay") int noOfDays) {
		List<YakDto> yaksList = stockService.getHerdDetailOnAnyDay(noOfDays);
		HerdDetail herdDetail =new HerdDetail();
		herdDetail.addYaks(yaksList);
		return herdDetail;
	}
	
	
	
}
