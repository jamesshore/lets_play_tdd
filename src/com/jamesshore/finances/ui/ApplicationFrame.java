package com.jamesshore.finances.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jamesshore.finances.domain.*;

public class ApplicationFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static final String TITLE = "Financial Projector";
	public static final Point INITIAL_POSITION = new Point(400, 300);
	public static final Dimension INITIAL_SIZE = new Dimension(900, 400);

	public ApplicationFrame() {
		super(TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(INITIAL_POSITION);
		setSize(INITIAL_SIZE);
		addComponents();
	}

	private void addComponents() {
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(BorderLayout.CENTER, forecastTable());
		contentPane.add(BorderLayout.NORTH, startingBalanceField());
	}

	private JTextField startingBalanceField() {
		JTextField field = new JTextField();
		
		field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		});
		
		return field;
		
//		button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				button.setText("The world will end in " + (Math.random() * 100000) + " seconds");
//				for (int i = 0; i < tableModel.getRowCount(); i++) {
//					int cell = (Integer)tableModel.getValueAt(i, 1);
//					tableModel.setValueAt((int)(cell * 1.03), i, 1);
//				}
//			}
//		});
		
	}
	
	private Component forecastTable() {
		forecastTable = new ForecastTable(new StockMarketTableModel(stockMarket()));
		return new JScrollPane(forecastTable);
	}

	private StockMarketProjection stockMarket() {
		Year startingYear = new Year(2010);
		Year endingYear = new Year(2050);
		Dollars startingBalance = new Dollars(10000);
		Dollars startingPrincipal = new Dollars(7000);
		GrowthRate interestRate = new GrowthRate(10);
		TaxRate capitalGainsTaxRate = new TaxRate(25);
		return new StockMarketProjection(startingYear, endingYear, startingBalance, startingPrincipal, interestRate, capitalGainsTaxRate, new Dollars(695));
	}
	
}
