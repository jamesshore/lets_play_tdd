package com.jamesshore.finances;

import java.awt.*;
import javax.swing.*;

public class Application extends JFrame {
	private static final long serialVersionUID = 1L;

	public Application() {
		this.setSize(900, 400);
		this.setLocation(400, 300);
		
		Container content = this.getContentPane();
		content.add(table());
	}

	private JScrollPane table() {
		StockMarketTableModel model = new StockMarketTableModel(2010, 2050, new Dollars(10000), new Dollars(7000), new InterestRate(10), new TaxRate(25));
		JTable table = new JTable(model);
		return new JScrollPane(table);
	}
	
	public static void main(String[] args) {
		new Application().setVisible(true);
	}
	
}
