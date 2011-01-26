package com.jamesshore.finances.ui;

import java.awt.event.*;
import javax.swing.*;
import com.jamesshore.finances.domain.*;

// If you ever want to subclass this class, be careful of race conditions with the event handler in
// the constructor. It could execute before the subclass constructor.
public final class DollarsTextField extends JTextField {
	private static final long serialVersionUID = 1L;
	
	public DollarsTextField(Dollars dollars) {
		this.setText(dollars.toString());
		this.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				DollarsTextField.this.setText(getDollars().toString());
			}
		});
	}

	public Dollars getDollars() {
		return Dollars.parse(getText());
	}

}
