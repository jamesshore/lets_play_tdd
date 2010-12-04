package com.jamesshore.finances.domain;

import static org.junit.Assert.*;
import org.junit.*;


public class _ApplicationModelTest {

	@Test
	public void shouldStartWithDefaultStockMarket() {
		ApplicationModel model = new ApplicationModel();
		StockMarketProjection projection = model.stockMarketProjection();
		
		StockMarketYear startingYear = projection.getYearOffset(0);
		assertEquals(ApplicationModel.DEFAULT_STARTING_YEAR, startingYear.year());
		assertEquals(ApplicationModel.DEFAULT_STARTING_BALANCE, startingYear.startingBalance());
		assertEquals(ApplicationModel.DEFAULT_STARTING_COST_BASIS, startingYear.startingCostBasis());
		assertEquals(ApplicationModel.DEFAULT_GROWTH_RATE, startingYear.growthRate());
		assertEquals(ApplicationModel.DEFAULT_CAPITAL_GAINS_TAX_RATE, startingYear.capitalGainsTaxRate());
		
		assertEquals(41, projection.numberOfYears());
	}
	
}
