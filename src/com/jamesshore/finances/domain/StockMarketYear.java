package com.jamesshore.finances.domain;

import com.jamesshore.finances.values.*;

public class StockMarketYear {

	private Year year;
	private Dollars startingBalance;
	private Dollars costBasis;
	private GrowthRate growthRate;
	private TaxRate capitalGainsTaxRate;
	private Dollars totalSellOrders;
	
	public StockMarketYear(Year year, Dollars startingBalance, Dollars costBasis, GrowthRate growthRate, TaxRate capitalGainsTaxRate) {
		this.year = year;
		this.startingBalance = startingBalance;
		this.costBasis = costBasis;
		this.growthRate = growthRate;
		this.capitalGainsTaxRate = capitalGainsTaxRate;
		this.totalSellOrders = new ValidDollars(0);
	}

	public Year year() {
		return year;
	}

	public Dollars startingBalance() {
		return startingBalance;
	}

	public Dollars startingCostBasis() {
		return costBasis;
	}

	private Dollars startingCapitalGains() {
		return startingBalance().minus(startingCostBasis());
	}

	public GrowthRate growthRate() {
		return growthRate;
	}

	public TaxRate capitalGainsTaxRate() {
		return capitalGainsTaxRate;
	}

	public void sell(Dollars amount) {
		this.totalSellOrders = totalSellOrders.plus(amount);
	}

	private Dollars capitalGainsWithdrawn() {
		return Dollars.min(startingCapitalGains(), totalSellOrders());
	}

	public Dollars capitalGainsTaxIncurred() {
		return capitalGainsTaxRate.compoundTaxFor(capitalGainsWithdrawn());
	}

	public Dollars totalSellOrders() {
		return totalSellOrders;
	}

	public Dollars totalSold() {
		return totalSellOrders().plus(capitalGainsTaxIncurred());
	}

	public Dollars growth() {
		return growthRate.growthFor(startingBalance.minus(totalSold()));
	}

	public Dollars endingBalance() {
		return startingBalance.minus(totalSold()).plus(growth());
	}

	public Dollars endingCostBasis() {
		Dollars purchasesSold = totalSold().subtractToZero(startingCapitalGains());
		return startingCostBasis().minus(purchasesSold);
	}

	public StockMarketYear nextYear() {
		return new StockMarketYear(year.nextYear(), this.endingBalance(), this.endingCostBasis(), this.growthRate(), this.capitalGainsTaxRate());
	}

}
