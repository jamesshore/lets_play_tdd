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

	private ApplicationModel applicationModel = new ApplicationModel();

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
		
		//SPIKE
		field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StockMarketTableModel model = applicationModel.stockMarketTableModel();
				model.setValueAt("HI!", 0, 0);
				model.fireTableDataChanged();
				
//				applicationModel.setStartingBalance(field.getText());
			}
		});
		
		return field;
	}
	
	private Component forecastTable() {
		return new JScrollPane(new ForecastTable(applicationModel.stockMarketTableModel()));
	}

}
