package com.jamesshore.finances;

public class StockMarket {

	private final int startingYear;
	private final int endingYear;
	private final Dollars startingBalance;
	private final Dollars startingPrincipal;
	private final InterestRate interestRate;
	private final TaxRate capitalGainsTaxRate;

	public StockMarket(int startingYear, int endingYear, Dollars startingBalance, Dollars startingPrincipal, InterestRate interestRate, TaxRate capitalGainsTaxRate) {
		this.startingYear = startingYear;
		this.endingYear = endingYear;
		this.startingBalance = startingBalance;
		this.startingPrincipal = startingPrincipal;
		this.interestRate = interestRate;
		this.capitalGainsTaxRate = capitalGainsTaxRate;
	}

	public StockMarketYear getYear(int offset) {
		StockMarketYear year = new StockMarketYear(startingBalance, startingPrincipal, interestRate, capitalGainsTaxRate);
		for (int i = 0; i < offset; i++) {
			year = year.nextYear();
		}
		return year;
	}

	public int numberOfYears() {
		return endingYear - startingYear + 1;
	}

}
