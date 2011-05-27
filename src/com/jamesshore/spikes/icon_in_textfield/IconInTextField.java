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
		content.add("Center", iconTextFieldWithGridBag());
		// content.add("Center", iconTextFieldWithOverlay());
		// content.add("Center", textField());
		// content.add("East", icon());
	}

	private Component iconTextFieldWithOverlay() {
		JPanel panel = new JPanel();
		panel.setLayout(new OverlayLayout(panel));

		JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		iconPanel.setOpaque(false);
		iconPanel.add(icon());

		JPanel gridbag = new JPanel();
		gridbag.setLayout(new GridBagLayout());
		gridbag.add(textField(), new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		panel.add(iconPanel);
		panel.add(gridbag);
		return panel;
	}

	private Component iconTextFieldWithGridBag() {
		JPanel gridbag = new JPanel();
		gridbag.setLayout(new GridBagLayout());

		gridbag.add(textField(), new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		gridbag.add(icon(), new GridBagConstraints(1, 0, 1, 1, 0, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

		return gridbag;
		// Box box = Box.createVerticalBox();
		// box.add(textField());
		// JPanel borderPanel = new JPanel();
		// borderPanel.setLayout(new BorderLayout());
		// borderPanel.add("Center", textField());
		// borderPanel.add("East", icon());
		// box.add(borderPanel);
		// box.add(Box.createVerticalGlue());
		// return box;
	}

	private Component textField() {
		final JTextField field = new JTextField();
		field.setText("foodle");
		return field;
	}

	private JLabel icon() {
		JLabel iconLabel = new JLabel();
		URL iconUrl = getClass().getResource("invalid_dollars.png");
		ImageIcon icon = new ImageIcon(iconUrl, "Invalid dollar amount");
		iconLabel.setIcon(icon);
		iconLabel.setBackground(Color.WHITE);
		iconLabel.setOpaque(false);
		return iconLabel;
	}

	public static void main(String[] args) {
		new IconInTextField().setVisible(true);
	}

}
