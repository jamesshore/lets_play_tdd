package com.jamesshore.finances.domain;

import javax.swing.table.*;
import com.jamesshore.finances.ui.*;

public class ApplicationModel {

	public static final Year DEFAULT_STARTING_YEAR = new Year(2010);
	public static final Year DEFAULT_ENDING_YEAR = new Year(2050);
	public static final Dollars DEFAULT_STARTING_BALANCE = new Dollars(10000);
	public static final Dollars DEFAULT_STARTING_COST_BASIS = new Dollars(7000);
	public static final GrowthRate DEFAULT_GROWTH_RATE = new GrowthRate(10);
	public static final TaxRate DEFAULT_CAPITAL_GAINS_TAX_RATE = new TaxRate(25);

	public StockMarketProjection stockMarketProjection() {
		StockMarketYear firstYear = new StockMarketYear(
			DEFAULT_STARTING_YEAR, 
			DEFAULT_STARTING_BALANCE, 
			DEFAULT_STARTING_COST_BASIS, 
			DEFAULT_GROWTH_RATE, 
			DEFAULT_CAPITAL_GAINS_TAX_RATE
		);
		return new StockMarketProjection(firstYear, DEFAULT_ENDING_YEAR, new Dollars(695));
	}

	public StockMarketTableModel stockMarketTableModel() {
		return new StockMarketTableModel(stockMarketProjection());
	}
	

}
