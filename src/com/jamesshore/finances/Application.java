package com.jamesshore.finances;

import java.awt.*;
import javax.swing.*;
import com.jamesshore.finances.domain.*;
import com.jamesshore.finances.ui.*;

public class Application extends JFrame {
	private static final long serialVersionUID = 1L;

	public Application() {
		this.setSize(900, 400);
		this.setLocation(400, 300);
		
		Container content = this.getContentPane();
		content.add(table());
	}

	private JScrollPane table() {
		StockMarketTableModel model = new StockMarketTableModel(stockMarket());
		JTable table = new ForecastTable(model);
		return new JScrollPane(table);
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
	
	public static void main(String[] args) {
		new Application().setVisible(true);
	}
	
}
