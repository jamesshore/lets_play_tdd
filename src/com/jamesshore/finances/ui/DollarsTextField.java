package com.jamesshore.finances.ui;

import javax.swing.*;
import com.jamesshore.finances.domain.*;

public class DollarsTextField extends JFormattedTextField {
	private static final long serialVersionUID = 1L;
	
	public DollarsTextField(Dollars dollars) {
		this.setText(dollars.toString());
	}

	public Dollars getDollars() {
		String text = getText();
		if (text.isEmpty()) return new Dollars(0);
		if (text.startsWith("$")) text = text.substring(1);
		
		int value = Integer.parseInt(text);
		return new Dollars(value);
	}

}
