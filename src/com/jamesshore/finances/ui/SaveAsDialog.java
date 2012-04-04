package com.jamesshore.finances.ui;

import java.awt.*;
import java.io.*;
import javax.swing.*;

public class SaveAsDialog extends FileDialog {
	private static final long serialVersionUID = 1L;

	private ApplicationModel model;

	public SaveAsDialog(Frame parentWindow, ApplicationModel model) {
		super(parentWindow, "Save As", FileDialog.SAVE);
		this.model = model;
	}

	public void displayModally() {
		this.setVisible(true);
		this.doSave(); // this line of code is untested
	}

	// non-private for testing purposes
	void doSave() {
		try {
			String directory = this.getDirectory();
			String file = this.getFile();

			if (file != null) model.save(new File(directory, file));
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Could not save file: " + e.getLocalizedMessage(), "Save File", JOptionPane.WARNING_MESSAGE);
		}
	}

}
