package com.jamesshore.finances.ui;

import javax.swing.*;
import com.jamesshore.finances.domain.*;

public class DollarsTextField extends JFormattedTextField {
	private static final long serialVersionUID = 1L;
	
	private Dollars dollars;

	public DollarsTextField(Dollars dollars) {
		this.dollars = dollars;
		this.add(new JFormattedTextField());
	}

	public Dollars getDollars() {
		return dollars;
	}

}
