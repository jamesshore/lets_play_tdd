package com.jamesshore.finances;

import javax.swing.table.*;

public class StockMarketTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_TITLES = {"Year", "Starting Balance", "Starting Principal", "Withdrawals", "Appreciation", "Ending Balance"};

	private int startingYear;
	private int endingYear;
	private StockMarketYear marketYear;

	public StockMarketTableModel(int startingYear, int endingYear, Dollars startingBalance, Dollars startingPrincipal, InterestRate interestRate, TaxRate capitalGainsTaxRate) {
		this.startingYear = startingYear;
		this.endingYear = endingYear;
		this.marketYear = new StockMarketYear(startingBalance, startingPrincipal, interestRate, capitalGainsTaxRate);
	}

	@Override
	public int getColumnCount() {
		return COLUMN_TITLES.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return COLUMN_TITLES[column];
	}

	@Override
	public int getRowCount() {
		return endingYear - startingYear + 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 0: return startingYear + rowIndex;
			case 1: return marketYear.startingBalance();
			case 2: return marketYear.startingPrincipal();
			case 3: return marketYear.totalWithdrawn();
			case 4: return marketYear.appreciation();
			case 5: return marketYear.endingBalance();
			default: return "";
		}
	}

}
