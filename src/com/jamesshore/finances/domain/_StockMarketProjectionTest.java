package com.jamesshore.finances.domain;

import static org.junit.Assert.*;
import org.junit.*;

public class _StockMarketProjectionTest {

	private static final Year STARTING_YEAR = new Year(2010);
	private static final Year ENDING_YEAR = new Year(2050);
	private static final Dollars STARTING_BALANCE = ValidDollars.create(10000);
	private static final Dollars COST_BASIS = ValidDollars.create(7000);
	private static final GrowthRate GROWTH_RATE = new GrowthRate(10);
	private static final TaxRate CAPITAL_GAINS_TAX_RATE = new TaxRate(25);

	private StockMarketYear firstYear;
	
	@Before
	public void setup() {
		firstYear = new StockMarketYear(STARTING_YEAR, STARTING_BALANCE, COST_BASIS, GROWTH_RATE, CAPITAL_GAINS_TAX_RATE);
	}
	
	@Test
	public void stockMarketContainsMultipleYears() {
		StockMarketProjection account = new StockMarketProjection(firstYear, ENDING_YEAR, ValidDollars.create(0));
		assertEquals("# of years", 41, account.numberOfYears());
		assertEquals(STARTING_BALANCE, account.getYearOffset(0).startingBalance());
		assertEquals(ValidDollars.create(11000), account.getYearOffset(1).startingBalance());
		assertEquals(ValidDollars.create(12100), account.getYearOffset(2).startingBalance());
		assertEquals(new Year(2050), account.getYearOffset(40).year());
	}
	
	@Test
	public void stockMarketWithdrawsAStandardAmountEveryYear() {
		StockMarketProjection account = new StockMarketProjection(firstYear, ENDING_YEAR, ValidDollars.create(10));
		assertEquals("year 0", ValidDollars.create(10), account.getYearOffset(0).totalSellOrders());
		assertEquals("year 1", ValidDollars.create(10), account.getYearOffset(1).totalSellOrders());
		assertEquals("year 40", ValidDollars.create(10), account.getYearOffset(40).totalSellOrders());
	}
		
	@Test
	public void noCumulativeRoundingErrorInInterestCalculations() {
		StockMarketProjection account = new StockMarketProjection(firstYear, ENDING_YEAR, ValidDollars.create(0));
		assertEquals(ValidDollars.create(497852), account.getYearOffset(40).endingBalance());
	}
	
	@Test
	public void capitalGainsTaxCalculationWorksTheSameWayAsSpreadsheet() {
		StockMarketProjection account = new StockMarketProjection(firstYear, ENDING_YEAR, ValidDollars.create(695));
		assertEquals(ValidDollars.create(2067), account.getYearOffset(40).endingBalance());
	}
	
}
