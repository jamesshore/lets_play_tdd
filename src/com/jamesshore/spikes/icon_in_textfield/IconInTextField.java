package com.jamesshore.spikes.icon_in_textfield;

import java.awt.*;
import java.net.*;
import javax.swing.*;

public class IconInTextField extends JFrame {
	private static final long serialVersionUID = 1L;

	public IconInTextField() {
		setTitle("Icon in Textfield Spike");
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addComponents();
		pack();
		this.setLocation(400, 200);
	}

	private void addComponents() {
		Container content = this.getContentPane();
		content.add("Center", iconTextField());
		// content.add("Center", textField());
		// content.add("East", icon());
	}

	private Component iconTextField() {
		JPanel borderPanel = new JPanel();
		borderPanel.setLayout(new BorderLayout());
		borderPanel.add("Center", textField());
		borderPanel.add("East", icon());
		return borderPanel;
	}

	private Component textField() {
		final JTextField field = new JTextField();
		field.setText("foodle");
		return field;
	}

	private Component icon() {
		JLabel iconLabel = new JLabel();
		URL iconUrl = getClass().getResource("invalid_dollars.png");
		ImageIcon icon = new ImageIcon(iconUrl, "Invalid dollar amount");
		iconLabel.setIcon(icon);
		return iconLabel;
	}

	public static void main(String[] args) {
		new IconInTextField().setVisible(true);
	}

}
