package com.jamesshore.finances.ui;

import java.awt.*;
import javax.swing.*;
import com.jamesshore.finances.domain.*;

public class ApplicationFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static final String TITLE = "Financial Projector";
	public static final Point INITIAL_POSITION = new Point(400, 300);
	public static final Dimension INITIAL_SIZE = new Dimension(900, 400);

	public ApplicationFrame() {
		super(TITLE);
		setLocation(INITIAL_POSITION);
		setSize(INITIAL_SIZE);
		getContentPane().add(forecastTable());
	}

	private Component forecastTable() {
//		return new ForecastTable(new StockMarketTableModel(stockMarket()));
		return new JScrollPane(null);
	}

	private StockMarket stockMarket() {
		Year startingYear = new Year(2010);
		Year endingYear = new Year(2050);
		Dollars startingBalance = new Dollars(10000);
		Dollars startingPrincipal = new Dollars(7000);
		GrowthRate interestRate = new GrowthRate(10);
		TaxRate capitalGainsTaxRate = new TaxRate(25);
		return new StockMarket(startingYear, endingYear, startingBalance, startingPrincipal, interestRate, capitalGainsTaxRate, new Dollars(695));
	}
	
}
