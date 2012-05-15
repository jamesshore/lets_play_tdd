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

	private Year startingYear = DEFAULT_STARTING_YEAR;
	private Year endingYear = DEFAULT_ENDING_YEAR;
	private UserEnteredDollars startingBalance = DEFAULT_STARTING_BALANCE;
	private UserEnteredDollars startingCostBasis = DEFAULT_STARTING_COST_BASIS;
	private GrowthRate growthRate = DEFAULT_GROWTH_RATE;
	private TaxRate capitalGainsTaxRate = DEFAULT_CAPITAL_GAINS_TAX_RATE;
	private UserEnteredDollars yearlySpending = DEFAULT_YEARLY_SPENDING;

	private StockMarketTableModel stockMarketTableModel = new StockMarketTableModel(stockMarketProjection());
	private UserConfiguration saveFile;

	public ApplicationModel() {
	}

	// TODO: for testing only; delete?
	public ApplicationModel(UserConfiguration mockSaveFile) {

	}

	public StockMarketTableModel stockMarketTableModel() {
		return stockMarketTableModel;
	}

	public UserEnteredDollars startingBalance() {
		return startingBalance;
	}

	public UserEnteredDollars startingCostBasis() {
		return startingCostBasis;
	}

	public UserEnteredDollars yearlySpending() {
		return yearlySpending;
	}

	public void setStartingBalance(UserEnteredDollars startingBalance) {
		this.startingBalance = startingBalance;
		stockMarketTableModel.setProjection(stockMarketProjection());
	}

	public void setStartingCostBasis(UserEnteredDollars startingCostBasis) {
		this.startingCostBasis = startingCostBasis;
		stockMarketTableModel.setProjection(stockMarketProjection());
	}

	public void setYearlySpending(UserEnteredDollars yearlySpending) {
		this.yearlySpending = yearlySpending;
		stockMarketTableModel.setProjection(stockMarketProjection());
	}

	public StockMarketProjection stockMarketProjection() {
		StockMarketYear firstYear = new StockMarketYear(startingYear, startingBalance, startingCostBasis, growthRate, capitalGainsTaxRate);
		return new StockMarketProjection(firstYear, endingYear, yearlySpending);
	}

	public void save(File path) throws IOException {
		this.saveFile = new UserConfiguration(path);
		saveFile.save(new UserEnteredDollars("foo"), new UserEnteredDollars("bar"), new UserEnteredDollars("baz"));
	}

	public File saveFilePathOrNullIfNotSaved() {
		if (saveFile == null) return null;
		else return saveFile.path();
	}

	public boolean fileHasEverBeenSaved() {
		return saveFile.hasEverBeenSaved();
	}

}
