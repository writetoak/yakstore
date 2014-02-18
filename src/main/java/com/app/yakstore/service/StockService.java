package com.app.yakstore.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.yakstore.dao.YakDao;
import com.app.yakstore.domain.Stock;
import com.app.yakstore.domain.Yak;
import com.app.yakstore.vo.ItemsEnum;
import com.app.yakstore.vo.YakDto;

@Service
public class StockService 
{
	@Autowired
	private YakDao yakDao;
	
	private int noOfDaysInYakYear = 100;
	private float maxAgeOfYakInDays = 10 * noOfDaysInYakYear;
	
	
	public Stock getStockDetailOnAnyDay(int nthDay)
	{
		List<Yak> dayZeroYaksDetails = yakDao.getAllYaksOnDayZero();
		Stock stock = new Stock(); 
		
		float totalMilk = 0;
		int skinsAvailable = 0;
		
		for (Yak yak : dayZeroYaksDetails) 
		{
			totalMilk += yak.milkProducedOn(nthDay);
			skinsAvailable += yak.getSkinsAvailable(nthDay);
			
			System.out.println(yak+","+yak.milkProducedOn(nthDay)+", skins=>"+yak.getSkinsAvailable(nthDay));
		}
		
		stock.addItem(ItemsEnum.MILK, totalMilk);
		stock.addItem(ItemsEnum.SKINS, skinsAvailable);
	
		return stock;
	}
	
	
	
	
	public List<YakDto> getHerdDetailOnAnyDay(int noOfDays)
	{
		List<Yak> dayZeroYaksDetails = yakDao.getAllYaksOnDayZero();
		List<YakDto> yaksDetailsOnAskedDay = new LinkedList<YakDto>();
		//float noOfDaysToBeAdded = noOfDays;
		YakDto yakDto= null;
		
		for (Yak yak : dayZeroYaksDetails) 
		{
			yakDto = new YakDto(yak.getName(), yak.getAgeInYears(), yak.getAgeInYears(), yak.getNoOfDaysToGetReadyForShave()); 
			
			if((yakDto.getAge() * noOfDaysInYakYear) < maxAgeOfYakInDays)
			{
				//yakDto.setAge(yak.getAgeInYears() + (noOfDaysToBeAdded/noOfDaysInYakYear));
				yakDto.addDaysInAge(noOfDays);
				yaksDetailsOnAskedDay.add(yakDto);
			}
				
		}
		
		return yaksDetailsOnAskedDay;
	}




	public YakDao getYakDao() {
		return yakDao;
	}




	public void setYakDao(YakDao yakDao) {
		this.yakDao = yakDao;
	}
	
	
	
	
}
