package com.jamesshore.spikes.icon_in_textfield;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;

public class IconInTextField extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel iconLabel;

	public IconInTextField() {
		setTitle("Icon in Textfield Spike");
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addComponents();
		pack();
		this.setLocation(400, 200);
	}

	private void addComponents() {
		Container content = this.getContentPane();
		// content.add("Center", iconTextFieldWithGridBag());
		content.add("Center", iconTextFieldWithOverlayLayout());
		content.add("South", button());
		// content.add("Center", textField());
		// content.add("East", icon());
	}

	private Component iconTextFieldWithOverlayLayout() {
		JPanel panel = new JPanel();
		panel.setLayout(new OverlayLayout(panel));

		JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		iconPanel.setOpaque(false);
		iconPanel.add(icon());

		JPanel textPanel = new JPanel(new BorderLayout());
		textPanel.add(textField(), BorderLayout.NORTH);
		textPanel.setOpaque(false);

		// JPanel textPanel = new JPanel();
		// textPanel.setLayout(new GridBagLayout());
		// textPanel.add(textField(), new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
		// GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		panel.add(iconPanel);
		panel.add(textPanel);
		return panel;
	}

	@SuppressWarnings("unused")
	private Component iconTextFieldWithSideBySideLayout() {
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
		iconLabel = new JLabel();
		URL iconUrl = getClass().getResource("invalid_dollars.png");
		ImageIcon icon = new ImageIcon(iconUrl, "Invalid dollar amount");
		iconLabel.setIcon(icon);
		iconLabel.setBackground(Color.WHITE);
		iconLabel.setOpaque(false);

		return iconLabel;
	}

	private Component button() {
		final JButton button = new JButton("Toggle Warning");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iconLabel.setVisible(!iconLabel.isVisible());
			}
		});
		return button;
	}

	public static void main(String[] args) {
		new IconInTextField().setVisible(true);
	}

}
