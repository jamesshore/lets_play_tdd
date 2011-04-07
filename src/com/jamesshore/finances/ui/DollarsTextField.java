package com.jamesshore.finances.ui;

import java.awt.event.*;
import javax.swing.*;
import com.jamesshore.finances.domain.*;

// If you want to subclass this class, it's okay to remove the 'final' designator, but be careful of race 
// conditions with the event handler in the constructor. It could execute before the subclass constructor.
public final class DollarsTextField extends JTextField {
	private static final long serialVersionUID = 1L;

	public DollarsTextField(Dollars initialValue) {
		this.setText(initialValue.toString());
		this.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				Dollars dollars = getDollars();
				if (dollars.isValid()) DollarsTextField.this.setText(dollars.toString());
			}
		});
	}

	public Dollars getDollars() {
		return Dollars.parse(getText());
	}

}
