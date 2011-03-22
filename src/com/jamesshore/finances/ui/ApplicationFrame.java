package com.jamesshore.finances.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class ApplicationFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public static final String TITLE = "Financial Projector";
	public static final Point INITIAL_POSITION = new Point(400, 300);
	public static final Dimension INITIAL_SIZE = new Dimension(900, 400);

	private ApplicationModel applicationModel;

	public ApplicationFrame(ApplicationModel applicationModel) {
		super(TITLE);
		this.applicationModel = applicationModel;
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

	public DollarsTextField startingBalanceField() {
		// TODO: need to test that startingBalanceField is initialized with correct value
		final DollarsTextField field = new DollarsTextField(applicationModel.startingBalance());

		field.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateApplicationModel();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateApplicationModel();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateApplicationModel();
			}

			private void updateApplicationModel() {
				applicationModel.setStartingBalance(field.getDollars());
			}
		});

		return field;
	}

	private Component forecastTable() {
		return new JScrollPane(new ForecastTable(applicationModel.stockMarketTableModel()));
	}

}
