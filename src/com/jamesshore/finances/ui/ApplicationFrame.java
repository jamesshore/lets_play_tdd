package com.jamesshore.finances.ui;

import java.awt.*;
import javax.swing.*;
import com.jamesshore.finances.ui.DollarsTextField.ChangeListener;

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
		final DollarsTextField field = new DollarsTextField(applicationModel.startingBalance());
		field.addTextChangeListener(new ChangeListener() {
			public void textChanged() {
				applicationModel.setStartingBalance(field.getDollars());
			}
		});
		return field;
	}

	private Component forecastTable() {
		return new JScrollPane(new ForecastTable(applicationModel.stockMarketTableModel()));
	}

}
