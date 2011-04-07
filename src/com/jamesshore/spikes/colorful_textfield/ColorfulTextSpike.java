package com.jamesshore.spikes.colorful_textfield;

import java.awt.*;
import javax.swing.*;

public class ColorfulTextSpike extends JFrame {
	private static final long serialVersionUID = 1L;

	public ColorfulTextSpike() {
		setTitle("Colorful Textfield Spike");
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addComponents();
		pack();
		this.setLocation(400, 200);
	}

	private void addComponents() {
		Container content = this.getContentPane();
		content.add("Center", textField());
	}

	private Component textField() {
		final JTextField field = new JTextField();
		field.setText("foodle");
		field.setForeground(Color.RED);
		return field;
	}

	public static void main(String[] args) {
		new ColorfulTextSpike().setVisible(true);
	}

}
