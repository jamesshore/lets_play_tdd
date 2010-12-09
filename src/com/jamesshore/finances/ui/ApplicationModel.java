package com.jamesshore.finances.ui;

import com.jamesshore.finances.domain.*;

public class ApplicationModel {

	public static final Year DEFAULT_STARTING_YEAR = new Year(2010);
	public static final Year DEFAULT_ENDING_YEAR = new Year(2050);
	public static final Dollars DEFAULT_STARTING_BALANCE = new Dollars(10000);
	public static final Dollars DEFAULT_STARTING_COST_BASIS = new Dollars(7000);
	public static final GrowthRate DEFAULT_GROWTH_RATE = new GrowthRate(10);
	public static final TaxRate DEFAULT_CAPITAL_GAINS_TAX_RATE = new TaxRate(25);
	
	private StockMarketTableModel stockMarketTableModel = new StockMarketTableModel(stockMarketProjection());

	public StockMarketTableModel stockMarketTableModel() {
		return stockMarketTableModel;
	}

	private StockMarketProjection stockMarketProjection() {
		StockMarketYear firstYear = new StockMarketYear(
			DEFAULT_STARTING_YEAR, 
			DEFAULT_STARTING_BALANCE, 
			DEFAULT_STARTING_COST_BASIS, 
			DEFAULT_GROWTH_RATE, 
			DEFAULT_CAPITAL_GAINS_TAX_RATE
		);
		return new StockMarketProjection(firstYear, DEFAULT_ENDING_YEAR, new Dollars(695));
	}
	
	//TODO: spike code to re-do
//	public void setStartingBalance(Dollars startingBalance) {
//		StockMarketYear firstYear = new StockMarketYear(
//			DEFAULT_STARTING_YEAR, 
//			startingBalance, 
//			DEFAULT_STARTING_COST_BASIS, 
//			DEFAULT_GROWTH_RATE, 
//			DEFAULT_CAPITAL_GAINS_TAX_RATE
//		);
//		StockMarketProjection projection = new StockMarketProjection(firstYear, DEFAULT_ENDING_YEAR, new Dollars(695));
//		stockMarketTableModel.setProjection(projection);
//	}

}
