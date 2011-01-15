package com.jamesshore.finances.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jamesshore.finances.domain.*;

public class DollarsTextField extends JTextField {
	private static final long serialVersionUID = 1L;
	
	public DollarsTextField(Dollars dollars) {
		this.setText(dollars.toString());
		this.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				System.out.print("G");
			}
			public void focusLost(FocusEvent e) {
				System.out.print("L");
				DollarsTextField.this.setText(getDollars().toString());
			}
		});
	}

	public Dollars getDollars() {
		String text = getText();
		if (text.startsWith("$")) text = text.substring(1);
		if (text.isEmpty()) return new Dollars(0);
		if (text.equals("-")) return new Dollars(0);
		text = text.replace(",", "");		
		
		return new Dollars(Double.parseDouble(text));
	}

}
