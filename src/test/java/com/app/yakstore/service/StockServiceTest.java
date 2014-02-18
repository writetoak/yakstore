package com.app.yakstore.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

import com.app.yakstore.dao.YakDao;
import com.app.yakstore.domain.LabYak;
import com.app.yakstore.domain.Stock;
import com.app.yakstore.domain.Yak;
import com.app.yakstore.vo.ItemsEnum;

public class StockServiceTest 
{
	private StockService stockService;
	private YakDao mockYakDao; 
	
	@Before
	public void setupUnitUnderTest() {
		
		mockYakDao = mock(YakDao.class);
		stockService = new StockService();
		stockService.setYakDao(mockYakDao);
	}
	
	@Test
	public void testStockForDay13()
	{
		List<Yak> yakList = new LinkedList<Yak>();
		LabYak yak1 = new LabYak("Betty-1", 4, "f");
		LabYak yak2 = new LabYak("Betty-2", 8, "f");
		LabYak yak3 = new LabYak("Betty-1", 9.5f, "f");
		
		yakList.add(yak1);
		yakList.add(yak2);
		yakList.add(yak3);
		
		when(mockYakDao.getAllYaksOnDayZero()).thenReturn(yakList);
		
		Stock stock = stockService.getStockDetailOnAnyDay(13);

		assertEquals(stock.getQuanity(ItemsEnum.MILK), 1104.4801f);
		assertEquals(stock.getQuanity(ItemsEnum.SKINS), 3f);
	}
	
	
	@Test
	public void testStockForDay14()
	{
		List<Yak> yakList = new LinkedList<Yak>();
		LabYak yak1 = new LabYak("Betty-1", 4, "f");
		LabYak yak2 = new LabYak("Betty-2", 8, "f");
		LabYak yak3 = new LabYak("Betty-1", 9.5f, "f");
		
		yakList.add(yak1);
		yakList.add(yak2);
		yakList.add(yak3);
		
		when(mockYakDao.getAllYaksOnDayZero()).thenReturn(yakList);
		
		Stock stock = stockService.getStockDetailOnAnyDay(14);
		
		assertEquals(stock.getQuanity(ItemsEnum.MILK), 1188.810f);
		assertEquals(stock.getQuanity(ItemsEnum.SKINS), 4f);
	}
}
