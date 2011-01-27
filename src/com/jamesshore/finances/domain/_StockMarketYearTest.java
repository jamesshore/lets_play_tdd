package com.jamesshore.finances.domain;

import static org.junit.Assert.*;
import org.junit.*;


public class _StockMarketYearTest {

	private static final Year YEAR = new Year(2010);
	private static final GrowthRate INTEREST_RATE = new GrowthRate(10);
	private static final Dollars STARTING_BALANCE = new ValidDollars(10000);
	private static final Dollars STARTING_PRINCIPAL = new ValidDollars(3000);
	private static final TaxRate CAPITAL_GAINS_TAX_RATE = new TaxRate(25);

	@Test
	public void startingValues() {
		StockMarketYear year = newYear();
		assertEquals("year", YEAR, year.year());
		assertEquals("starting balance", STARTING_BALANCE, year.startingBalance());
		assertEquals("starting principal", STARTING_PRINCIPAL, year.startingCostBasis());
		assertEquals("interest rate", INTEREST_RATE, year.growthRate());
		assertEquals("capital gains tax rate", CAPITAL_GAINS_TAX_RATE, year.capitalGainsTaxRate());
		assertEquals("total withdrawn default", new ValidDollars(0), year.totalSold());
	}
	
	@Test
	public void totalSold() {
		StockMarketYear year = newYear();
		assertEquals("no sales", new ValidDollars(0), year.totalSellOrders());
		year.sell(new ValidDollars(3000));
		assertEquals("one sale", new ValidDollars(3000), year.totalSellOrders());
		year.sell(new ValidDollars(750));
		year.sell(new ValidDollars(1350));
		assertEquals("multiple sales", new ValidDollars(5100), year.totalSellOrders());
	}
	
	@Test
	public void capitalGainsTax() {
		StockMarketYear year = newYear();
		year.sell(new ValidDollars(4000));
		assertEquals("capital gains tax includes tax on withdrawals to cover capital gains", new ValidDollars(1333), year.capitalGainsTaxIncurred());
		assertEquals("total withdrawn includes capital gains tax", new ValidDollars(5333), year.totalSold());
	}
	
	@Test
	public void treatAllWithdrawalsAsSubjectToCapitalGainsTaxUntilAllCapitalGainsHaveBeenSold() {
		StockMarketYear year = newYear();
		
		Dollars capitalGains = STARTING_BALANCE.minus(STARTING_PRINCIPAL);

		year.sell(new ValidDollars(500));
		assertEquals("pay tax on all entire withdrawal", new ValidDollars(167), year.capitalGainsTaxIncurred());
		year.sell(capitalGains);
		assertEquals("to match spreadsheet, we pay compounding tax on capital gains even when compounded amount is not capital gains", new ValidDollars(2333), year.capitalGainsTaxIncurred());
		year.sell(new ValidDollars(1000));
		assertEquals("pay no more tax once all capital gains withdrawn", new ValidDollars(2333), year.capitalGainsTaxIncurred());
	}
	
	@Test
	public void interestEarned() {
		StockMarketYear year = newYear();
		assertEquals("basic interest earned", new ValidDollars(1000), year.growth());
		year.sell(new ValidDollars(2000));
		assertEquals("withdrawals (which pay capital gains tax) don't earn interest", new ValidDollars(733), year.growth());
	}
	
	@Test
	public void endingPrincipal() {
		StockMarketYear year = newYear();
		year.sell(new ValidDollars(500));
		assertEquals("withdrawals less than capital gains do not reduce principal", STARTING_PRINCIPAL, year.endingCostBasis());
		year.sell(new ValidDollars(6500));
		
		Dollars totalWithdrawn = new ValidDollars(9333);
		Dollars capitalGains = new ValidDollars(7000);
		Dollars principalReducedBy = totalWithdrawn.minus(capitalGains);
		Dollars expectedPrincipal = STARTING_PRINCIPAL.minus(principalReducedBy);
		assertEquals("principal should be reduced by difference between total withdrawals and capital gains", expectedPrincipal, year.endingCostBasis());
		
		year.sell(new ValidDollars(1000));
		assertEquals("principal goes negative when we're overdrawn", new ValidDollars(-333), year.endingCostBasis());
	}

	@Test
	public void endingBalance() {
		StockMarketYear year = newYear();
		assertEquals("ending balance includes interest", new ValidDollars(11000), year.endingBalance());
		year.sell(new ValidDollars(1000));
		assertEquals("ending balance includes withdrawals (which pay capital gains tax) and interest", new ValidDollars(9533), year.endingBalance());
	}

	@Test
	public void nextYearStartingValuesMatchesThisYearEndingValues() {
		StockMarketYear thisYear = newYear();
		StockMarketYear nextYear = thisYear.nextYear();
		assertEquals("year", new Year(2011), nextYear.year());
		assertEquals("starting balance", thisYear.endingBalance(), nextYear.startingBalance());
		assertEquals("starting principal", thisYear.endingCostBasis(), nextYear.startingCostBasis());
		assertEquals("interest", thisYear.growthRate(), nextYear.growthRate());
		assertEquals("capital gains tax rate", thisYear.capitalGainsTaxRate(), nextYear.capitalGainsTaxRate());
	}

	private StockMarketYear newYear() {
		return new StockMarketYear(YEAR, STARTING_BALANCE, STARTING_PRINCIPAL, INTEREST_RATE, CAPITAL_GAINS_TAX_RATE);
	}

}
