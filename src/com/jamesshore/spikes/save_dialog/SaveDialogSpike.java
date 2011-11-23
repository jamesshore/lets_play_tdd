package com.jamesshore.spikes.save_dialog;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SaveDialogSpike extends JFrame {
	private static final long serialVersionUID = 1L;

	public SaveDialogSpike() {
		setTitle("Save Dialog Spike");
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addComponents();
		pack();
		this.setLocation(400, 200);
	}

	private void addComponents() {
		Container content = this.getContentPane();
		JButton button = new JButton("Save Dialog...");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showSaveDialog();
			}
		});
		content.add(button);
	}

	private void showSaveDialog() {
		FileDialog dialog = new FileDialog(this, "Save As", FileDialog.SAVE);
		dialog.setVisible(true);
		String fileSelected = dialog.getFile();
		if (fileSelected == null) JOptionPane.showMessageDialog(this, "Cancel button pushed");
		else JOptionPane.showMessageDialog(this, "Save button pushed: " + fileSelected);
	}

	public static void main(String[] args) {
		new SaveDialogSpike().setVisible(true);
	}

}
