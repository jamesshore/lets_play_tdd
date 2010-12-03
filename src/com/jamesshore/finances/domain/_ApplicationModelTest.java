package com.jamesshore.finances.domain;

import static org.junit.Assert.*;
import org.junit.*;


public class _ApplicationModelTest {

	@Test
	public void shouldStartWithDefaultStockMarket() {
		ApplicationModel model = new ApplicationModel();
		StockMarketProjection projection = model.stockMarketProjection();
		assertNotNull(projection);
	}
	
}
