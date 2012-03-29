package com.jamesshore.finances.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class ApplicationFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public static final String TITLE = "Financial Projector";
	public static final Point INITIAL_POSITION = new Point(400, 300);
	public static final Dimension INITIAL_SIZE = new Dimension(900, 400);

	private ApplicationModel model;
	private FileDialog saveAsDialog;

	public static void newWindow() {
		new ApplicationFrame(new ApplicationModel()).setVisible(true);
	}

	public ApplicationFrame(ApplicationModel applicationModel) {
		super(TITLE);
		this.model = applicationModel;
		configureWindow();
		addComponents();
	}

	private void configureWindow() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(INITIAL_POSITION);
		setSize(INITIAL_SIZE);
	}

	private void addComponents() {
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(BorderLayout.CENTER, forecastTable());
		contentPane.add(BorderLayout.NORTH, configurationPanel());

		setJMenuBar(menuBar());
		saveAsDialog = new FileDialog(ApplicationFrame.this, "Save As", FileDialog.SAVE);
	}

	private Component forecastTable() {
		return new JScrollPane(new ForecastTable(model.stockMarketTableModel()));
	}

	private ConfigurationPanel configurationPanel() {
		return new ConfigurationPanel(model);
	}

	private JMenuBar menuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(newMenuItem());
		fileMenu.add(closeMenuItem());
		fileMenu.add(saveAsMenuItem());
		menuBar.add(fileMenu);
		return menuBar;
	}

	private JMenuItem newMenuItem() {
		return menuItem("New", KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.META_MASK), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newWindow();
			}
		});
	}

	private JMenuItem closeMenuItem() {
		return menuItem("Close", KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.META_MASK), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private JMenuItem saveAsMenuItem() {
		return menuItem("Save As...", KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.SHIFT_MASK | InputEvent.META_MASK), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveAsDialog.setVisible(true);
				doSave(); // this line of code is untested
			}
		});
	}

	// non-private for testing purposes
	void doSave() {
		try {
			String directory = saveAsDialog.getDirectory();
			String file = saveAsDialog.getFile();

			if (file != null) model.save(new File(directory, file));
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(this, "foo", "Save File", JOptionPane.ERROR_MESSAGE);
			// JOptionPane.showMessageDialog(this, "Could not save file: " + e.getLocalizedMessage(), "Save File",
			// JOptionPane.WARNING_MESSAGE);
		}
	}

	private JMenuItem menuItem(String name, KeyStroke accelerator, ActionListener action) {
		JMenuItem newMenuItem = new JMenuItem(name);
		newMenuItem.setAccelerator(accelerator);
		newMenuItem.addActionListener(action);
		return newMenuItem;
	}
}
