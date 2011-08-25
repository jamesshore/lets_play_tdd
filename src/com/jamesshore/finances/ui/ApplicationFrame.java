package com.jamesshore.finances.ui;

import java.awt.*;
import javax.swing.*;

public class ApplicationFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public static final String TITLE = "Financial Projector";
	public static final Point INITIAL_POSITION = new Point(400, 300);
	public static final Dimension INITIAL_SIZE = new Dimension(900, 400);

	private ApplicationModel model;

	public ApplicationFrame(ApplicationModel applicationModel) {
		super(TITLE);
		this.model = applicationModel;
		configureWindow();
		createMenu();
		addComponents();
	}

	private void configureWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(INITIAL_POSITION);
		setSize(INITIAL_SIZE);
	}

	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(new JMenu("File"));

		setJMenuBar(menuBar);
	}

	private void addComponents() {
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(BorderLayout.CENTER, forecastTable());
		contentPane.add(BorderLayout.NORTH, configurationPanel());
	}

	private Component forecastTable() {
		return new JScrollPane(new ForecastTable(model.stockMarketTableModel()));
	}

	private ConfigurationPanel configurationPanel() {
		return new ConfigurationPanel(model);
	}

}
