package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import org.junit.*;
import com.jamesshore.finances.domain.*;

public class _ApplicationModelTest {

	private ApplicationModel model;

	@Before
	public void setup() {
		model = new ApplicationModel();
	}

	@Test
	public void shouldStartWithDefaultStockMarket() {
		StockMarketProjection projection = model.stockMarketTableModel().stockMarketProjection();

		StockMarketYear startingYear = projection.getYearOffset(0);
		assertEquals(ApplicationModel.DEFAULT_STARTING_YEAR, startingYear.year());
		assertEquals(ApplicationModel.DEFAULT_STARTING_BALANCE, startingYear.startingBalance());
		assertEquals(ApplicationModel.DEFAULT_STARTING_COST_BASIS, startingYear.startingCostBasis());
		assertEquals(ApplicationModel.DEFAULT_GROWTH_RATE, startingYear.growthRate());
		assertEquals(ApplicationModel.DEFAULT_CAPITAL_GAINS_TAX_RATE, startingYear.capitalGainsTaxRate());
		assertEquals(ApplicationModel.DEFAULT_YEARLY_SPENDING, startingYear.totalSellOrders());

		assertEquals(41, projection.numberOfYears());
	}

	@Test
	public void shouldOnlyHaveOneInstanceOfStockMarketTableModel() {
		assertTrue("should be same instance", model.stockMarketTableModel() == model.stockMarketTableModel());
	}

	@Test
	public void changingStartingBalanceShouldChangeStockMarketTableModel() {
		model.setStartingBalance(ValidDollars.create(123));
		assertEquals(ValidDollars.create(123), model.stockMarketTableModel().startingBalance());
	}

	@Test
	public void changingStartingCostBasisShouldChangeStockMarketTableModel() {
		model.setStartingCostBasis(ValidDollars.create(39));
		assertEquals(ValidDollars.create(39), model.stockMarketTableModel().startingCostBasis());
	}

}
