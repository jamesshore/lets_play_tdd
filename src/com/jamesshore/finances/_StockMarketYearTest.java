package com.jamesshore.finances;

import static org.junit.Assert.*;
import org.junit.*;


public class _StockMarketYearTest {

	private static final int INTEREST_RATE = 10;
	private static final int STARTING_PRINCIPAL = 3000;
	private static final int STARTING_BALANCE = 10000;

	@Test
	public void startingValues() {
		StockMarketYear year = newYear();
		assertEquals("starting balance", STARTING_BALANCE, year.startingBalance());
		assertEquals("starting principal", STARTING_PRINCIPAL, year.startingPrincipal());
		assertEquals("interest rate", INTEREST_RATE, year.interestRate());
		assertEquals("total withdrawn default", 0, year.totalWithdrawn(25));
	}
	
	@Test
	public void capitalGainsTax() {
		StockMarketYear year = newYear();
		year.withdraw(4000);
		assertEquals("capital gains tax includes tax on withdrawals to cover capital gains", 333, year.capitalGainsTaxIncurred(25));
		assertEquals("total withdrawn includes capital gains tax", 4333, year.totalWithdrawn(25));
	}
	
	@Test
	public void interestEarned() {
		StockMarketYear year = newYear();
		assertEquals("basic interest earned", 1000, year.interestEarned(25));
		year.withdraw(2000);
		assertEquals("withdrawals don't earn interest", 800, year.interestEarned(25));
		year.withdraw(2000);
		assertEquals("capital gains tax withdrawals don't earn interest", 566, year.interestEarned(25));
	}

	@Test
	public void endingPrincipal() {
		StockMarketYear year = newYear();
		year.withdraw(1000);
		assertEquals("ending principal considers withdrawals", 2000, year.endingPrincipal());
		year.withdraw(500);
		assertEquals("ending principal considers totals multiple withdrawals", 1500, year.endingPrincipal());
		year.withdraw(3000);
		assertEquals("ending principal never goes below zero", 0, year.endingPrincipal());
	}

	@Test
	public void endingBalance() {
		StockMarketYear year = newYear();
		assertEquals("ending balance includes interest", 11000, year.endingBalance(25));
		year.withdraw(1000);
		assertEquals("ending balance includes withdrawals", 9900, year.endingBalance(25));
		year.withdraw(3000);
		assertEquals("ending balance includes capital gains tax withdrawals", 6233, year.endingBalance(25));
	}

	@Test
	public void nextYearStartingValuesMatchesThisYearEndingValues() {
		StockMarketYear thisYear = newYear();
		StockMarketYear nextYear = thisYear.nextYear(25);
		assertEquals("starting balance", thisYear.endingBalance(25), nextYear.startingBalance());
		assertEquals("starting principal", thisYear.endingPrincipal(), nextYear.startingPrincipal());
		assertEquals("interest", thisYear.interestRate(), nextYear.interestRate());
	}

	private StockMarketYear newYear() {
		return new StockMarketYear(STARTING_BALANCE, STARTING_PRINCIPAL, INTEREST_RATE);
	}

}
