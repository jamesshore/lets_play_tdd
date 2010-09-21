package com.jamesshore.finances;

import javax.swing.table.*;

public class StockMarketTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_TITLES = {"Year", "Starting Balance", "Starting Principal", "Withdrawals", "Appreciation", "Ending Balance"};

	private int startingYear;
	private int endingYear;
	private StockMarketYear[] years;

	public StockMarketTableModel(int startingYear, int endingYear, Dollars startingBalance, Dollars startingPrincipal, InterestRate interestRate, TaxRate capitalGainsTaxRate) {
		this.startingYear = startingYear;
		this.endingYear = endingYear;
		populateYears(startingBalance, startingPrincipal, interestRate, capitalGainsTaxRate);
	}

	private void populateYears(Dollars startingBalance, Dollars startingPrincipal, InterestRate interestRate, TaxRate capitalGainsTaxRate) {
		this.years = new StockMarketYear[getRowCount()];
		years[0] = new StockMarketYear(startingBalance, startingPrincipal, interestRate, capitalGainsTaxRate);
		for (int i = 1; i < getRowCount(); i++) {
			years[i] = years[i - 1].nextYear(); 
		}
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
		StockMarketYear currentYear = years[rowIndex];
		switch (columnIndex) {
			case 0: return startingYear + rowIndex;
			case 1: return currentYear.startingBalance();
			case 2: return currentYear.startingPrincipal();
			case 3: return currentYear.totalWithdrawn();
			case 4: return currentYear.appreciation();
			case 5: return currentYear.endingBalance();
			default: return "";
		}
	}
}
