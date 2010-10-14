package com.jamesshore.finances.domain;

import static org.junit.Assert.*;
import org.junit.*;


public class _StockMarketYearTest {

	private static final Year YEAR = new Year(2010);
	private static final InterestRate INTEREST_RATE = new InterestRate(10);
	private static final Dollars STARTING_BALANCE = new Dollars(10000);
	private static final Dollars STARTING_PRINCIPAL = new Dollars(3000);
	private static final TaxRate CAPITAL_GAINS_TAX_RATE = new TaxRate(25);

	@Test
	public void startingValues() {
		StockMarketYear year = newYear();
		assertEquals("year", YEAR, year.year());
		assertEquals("starting balance", STARTING_BALANCE, year.startingBalance());
		assertEquals("starting principal", STARTING_PRINCIPAL, year.startingPrincipal());
		assertEquals("interest rate", INTEREST_RATE, year.interestRate());
		assertEquals("capital gains tax rate", CAPITAL_GAINS_TAX_RATE, year.capitalGainsTaxRate());
		assertEquals("total withdrawn default", new Dollars(0), year.totalWithdrawn());
	}
	
	@Test
	public void capitalGainsTax() {
		StockMarketYear year = newYear();
		year.withdraw(new Dollars(4000));
		assertEquals("capital gains tax includes tax on withdrawals to cover capital gains", new Dollars(1333), year.capitalGainsTaxIncurred());
		assertEquals("total withdrawn includes capital gains tax", new Dollars(5333), year.totalWithdrawn());
	}
	
	@Test
	public void treatAllWithdrawalsAsSubjectToCapitalGainsTaxUntilAllCapitalGainsHaveBeenSold() {
		StockMarketYear year = newYear();
		
		Dollars capitalGains = STARTING_BALANCE.minus(STARTING_PRINCIPAL);

		year.withdraw(new Dollars(500));
		assertEquals("pay tax on all entire withdrawal", new Dollars(167), year.capitalGainsTaxIncurred());
		year.withdraw(capitalGains);
		assertEquals("pay tax on all withdrawals until all capital gains withdrawn", new Dollars(2333), year.capitalGainsTaxIncurred());
		year.withdraw(new Dollars(1000));
		assertEquals("pay no more tax once all capital gains withdrawn", new Dollars(2333), year.capitalGainsTaxIncurred());
	}
	
	@Test
	public void interestEarned() {
		StockMarketYear year = newYear();
		assertEquals("basic interest earned", new Dollars(1000), year.appreciation());
		year.withdraw(new Dollars(2000));
		assertEquals("withdrawals (which pay capital gains tax) don't earn interest", new Dollars(733), year.appreciation());
	}

	@Test
	public void endingPrincipal() {
		StockMarketYear year = newYear();
		year.withdraw(new Dollars(1000));
		assertEquals("ending principal considers withdrawals", new Dollars(2000), year.endingPrincipal());
		year.withdraw(new Dollars(500));
		assertEquals("ending principal considers totals multiple withdrawals", new Dollars(1500), year.endingPrincipal());
		year.withdraw(new Dollars(3000));
		assertEquals("ending principal never goes below zero", new Dollars(0), year.endingPrincipal());
	}

	@Test
	public void endingBalance() {
		StockMarketYear year = newYear();
		assertEquals("ending balance includes interest", new Dollars(11000), year.endingBalance());
		year.withdraw(new Dollars(1000));
		assertEquals("ending balance includes withdrawals (which pay capital gains tax) and interest", new Dollars(9533), year.endingBalance());
	}

	@Test
	public void nextYearStartingValuesMatchesThisYearEndingValues() {
		StockMarketYear thisYear = newYear();
		StockMarketYear nextYear = thisYear.nextYear();
		assertEquals("year", new Year(2011), nextYear.year());
		assertEquals("starting balance", thisYear.endingBalance(), nextYear.startingBalance());
		assertEquals("starting principal", thisYear.endingPrincipal(), nextYear.startingPrincipal());
		assertEquals("interest", thisYear.interestRate(), nextYear.interestRate());
		assertEquals("capital gains tax rate", thisYear.capitalGainsTaxRate(), nextYear.capitalGainsTaxRate());
	}

	private StockMarketYear newYear() {
		return new StockMarketYear(YEAR, STARTING_BALANCE, STARTING_PRINCIPAL, INTEREST_RATE, CAPITAL_GAINS_TAX_RATE);
	}

}
