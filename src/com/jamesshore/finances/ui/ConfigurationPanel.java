package com.jamesshore.finances.ui;

import javax.swing.*;

public class ConfigurationPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public ConfigurationPanel(ApplicationModel model) {
		this.add(new DollarsTextField(model.startingBalance()));
	}

}
