package com.jamesshore.finances.domain;

public class ApplicationModel {

	public StockMarketProjection stockMarketProjection() {
//		Year startingYear = new Year(2010);
//		Year endingYear = new Year(2050);
//		Dollars startingBalance = new Dollars(10000);
//		Dollars startingPrincipal = new Dollars(7000);
//		GrowthRate interestRate = new GrowthRate(10);
//		TaxRate capitalGainsTaxRate = new TaxRate(25);

		
		Year startingYear = new Year(0);
		Year endingYear = new Year(0);
		Dollars startingBalance = new Dollars(0);
		Dollars startingPrincipal = new Dollars(0);
		GrowthRate interestRate = new GrowthRate(1);
		TaxRate capitalGainsTaxRate = new TaxRate(1);

		return new StockMarketProjection(startingYear, endingYear, startingBalance, startingPrincipal, interestRate, capitalGainsTaxRate, new Dollars(695));
	}

}
