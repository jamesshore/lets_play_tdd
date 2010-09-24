package com.jamesshore.finances;

import javax.swing.table.*;

public class StockMarketTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_TITLES = {"Year", "Starting Balance", "Starting Principal", "Withdrawals", "Appreciation", "Ending Balance"};

	private int startingYear;
	private StockMarket market;

	public StockMarketTableModel(int startingYear, int endingYear, StockMarket market) {
		this.startingYear = startingYear;
		this.market = market;
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
		return market.numberOfYears();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		StockMarketYear currentYear = market.getYear(rowIndex);
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
