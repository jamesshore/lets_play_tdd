package com.jamesshore.spikes.alert_dialog;

import javax.swing.*;

public class AlertDialogSpike extends JFrame {
	private static final long serialVersionUID = 1L;

	private static String MESSAGE = "Could not frobble the wizzits. We need this dialog to automatically wrap really long lines so that we don't look like we're trapped in the 80's with programmer UI's but instead actually care about the quality of our application, or at least the quality that we convey to our customers, few in number though they may be.";

	public AlertDialogSpike() {
		setTitle("Save Dialog Spike");
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		pack();
		this.setLocation(500, 300);
		showSwingDialog();
		System.exit(0);
	}

	private void showSwingDialog() {
		JTextPane textPane = new JTextPane();
		textPane.setText(MESSAGE);
		textPane.setOpaque(false);
		JOptionPane.showMessageDialog(this, textPane, "Error", JOptionPane.WARNING_MESSAGE);
	}

	public static void main(String[] args) {
		new AlertDialogSpike().setVisible(true);
	}

}
