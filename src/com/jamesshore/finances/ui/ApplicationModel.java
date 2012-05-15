package com.jamesshore.finances.ui;

import java.io.*;
import com.jamesshore.finances.domain.*;
import com.jamesshore.finances.persistence.*;
import com.jamesshore.finances.values.*;

public class ApplicationModel {

	public static final Year DEFAULT_STARTING_YEAR = new Year(2010);
	public static final Year DEFAULT_ENDING_YEAR = new Year(2050);
	public static final UserEnteredDollars DEFAULT_STARTING_BALANCE = new UserEnteredDollars("10000");
	public static final UserEnteredDollars DEFAULT_STARTING_COST_BASIS = new UserEnteredDollars("7000");
	public static final GrowthRate DEFAULT_GROWTH_RATE = new GrowthRate(10);
	public static final TaxRate DEFAULT_CAPITAL_GAINS_TAX_RATE = new TaxRate(25);
	public static final UserEnteredDollars DEFAULT_YEARLY_SPENDING = new UserEnteredDollars("695");

	private UserEnteredDollars yearlySpending = DEFAULT_YEARLY_SPENDING;

	private Year startingYear = DEFAULT_STARTING_YEAR;
	private Year endingYear = DEFAULT_ENDING_YEAR;
	private GrowthRate growthRate = DEFAULT_GROWTH_RATE;
	private TaxRate capitalGainsTaxRate = DEFAULT_CAPITAL_GAINS_TAX_RATE;

	private UserConfiguration configuration;
	private StockMarketTableModel stockMarketTableModel;

	public ApplicationModel() {
		configuration = new UserConfiguration();
		configuration.startingBalance = DEFAULT_STARTING_BALANCE; // TODO: Move to UserConfiguration
		configuration.startingCostBasis = DEFAULT_STARTING_COST_BASIS;

		stockMarketTableModel = new StockMarketTableModel(stockMarketProjection());
	}

	public StockMarketTableModel stockMarketTableModel() {
		return stockMarketTableModel;
	}

	public UserEnteredDollars startingBalance() {
		return configuration.startingBalance;
	}

	public UserEnteredDollars startingCostBasis() {
		return configuration.startingCostBasis;
	}

	public UserEnteredDollars yearlySpending() {
		return yearlySpending;
	}

	public void setStartingBalance(UserEnteredDollars startingBalance) {
		configuration.startingBalance = startingBalance;
		stockMarketTableModel.setProjection(stockMarketProjection());
	}

	public void setStartingCostBasis(UserEnteredDollars startingCostBasis) {
		configuration.startingCostBasis = startingCostBasis;
		stockMarketTableModel.setProjection(stockMarketProjection());
	}

	public void setYearlySpending(UserEnteredDollars yearlySpending) {
		this.yearlySpending = yearlySpending;
		stockMarketTableModel.setProjection(stockMarketProjection());
	}

	public StockMarketProjection stockMarketProjection() {
		StockMarketYear firstYear = new StockMarketYear(startingYear, configuration.startingBalance, configuration.startingCostBasis, growthRate, capitalGainsTaxRate);
		return new StockMarketProjection(firstYear, endingYear, yearlySpending);
	}

	public void save(File path) throws IOException {
		// this.configuration = new UserConfiguration();
		// configuration.costBasis = new UserEnteredDollars("foo");
		// configuration.startingBalance = new UserEnteredDollars("bar");
		// configuration.yearlySpending = new UserEnteredDollars("baz");
		// configuration.save(path);
	}

	public File lastSavedPathOrNullIfNeverSaved() {
		if (configuration == null) return null;
		else return configuration.lastSavedPathOrNullIfNeverSaved();
	}

}
