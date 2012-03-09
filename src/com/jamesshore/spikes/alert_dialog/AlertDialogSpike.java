package com.jamesshore.spikes.alert_dialog;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AlertDialogSpike extends JFrame {
	private static final long serialVersionUID = 1L;

	public AlertDialogSpike() {
		setTitle("Save Dialog Spike");
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addComponents();
		pack();
		this.setLocation(400, 200);
	}

	private void addComponents() {
		Container content = this.getContentPane();
		JButton button = new JButton("Alert Dialog...");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showAlertDialog();
			}
		});
		content.add(button);
	}

	private void showAlertDialog() {
		JOptionPane.showMessageDialog(this, "Could not frobble the wizzits.", "Error", JOptionPane.WARNING_MESSAGE);
	}

	public static void main(String[] args) {
		new AlertDialogSpike().setVisible(true);
	}

}
