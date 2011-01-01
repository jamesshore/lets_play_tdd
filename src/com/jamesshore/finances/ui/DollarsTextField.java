package com.jamesshore.finances.ui;

import java.text.*;
import javax.swing.*;
import com.jamesshore.finances.domain.*;

public class DollarsTextField extends JFormattedTextField {
	private static final long serialVersionUID = 1L;
	
	private Dollars dollars;

	public DollarsTextField(Dollars dollars) {
		this.dollars = dollars;
		this.setText(dollars.toString());
	}

	public Dollars getDollars() {
		NumberFormat format = NumberFormat.getCurrencyInstance();
		try {
			Number value = format.parse(this.getText());
			return new Dollars(value.doubleValue());
		}
		catch (ParseException e) {
			throw new RuntimeException(e);  // TODO: implement
		}
	}

}
