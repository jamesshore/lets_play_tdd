package com.jamesshore.finances;

import static org.junit.Assert.*;
import org.junit.*;

public class _StockMarketTest {

	private static final int STARTING_YEAR = 2010;
	private static final int ENDING_YEAR = 2050;
	private static final Dollars STARTING_BALANCE = new Dollars(10000);
	private static final Dollars STARTING_PRINCIPAL = new Dollars(7000);
	private static final InterestRate INTEREST_RATE = new InterestRate(10);
	private static final TaxRate CAPITAL_GAINS_TAX_RATE = new TaxRate(25);

	@Test
	public void stockMarketContainsMultipleYears() {
		StockMarket account = new StockMarket(STARTING_YEAR, ENDING_YEAR, STARTING_BALANCE, STARTING_PRINCIPAL, INTEREST_RATE, CAPITAL_GAINS_TAX_RATE);
		assertEquals("# of years", 41, account.numberOfYears());
		assertEquals(STARTING_BALANCE, account.getYear(0).startingBalance());
		assertEquals(new Dollars(11000), account.getYear(1).startingBalance());
		assertEquals(new Dollars(12100), account.getYear(2).startingBalance());
	}
	
}
