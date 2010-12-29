package com.jamesshore.spikes.formatted_text;

import java.awt.*;
import java.beans.*;
import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.*;
import javax.swing.text.*;

public class FormattedTextSpike extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton button = new JButton("Button");

	public FormattedTextSpike() {
		setTitle("Formatted Text Spike");
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addComponents();
		pack();
		this.setLocation(400, 200);
	}
	
	private void addComponents() {
		Container content = this.getContentPane();
		content.add("Center", textField());
		content.add("South", button);
	}
	
	private Component textField() {
		JFormattedTextField field = new JFormattedTextField(formatterFactory());
		field.setValue(new Integer(30));
		field.addPropertyChangeListener("value", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println("propertyChange event fired");
				button.setText(evt.getNewValue().toString());
			}
		});
		
		return field;
	}
	
	private AbstractFormatterFactory formatterFactory() {
		AbstractFormatter editFormatter = new NumberFormatter();
		DefaultFormatterFactory factory = new DefaultFormatterFactory(editFormatter);
		
		return factory;
	}

	public static void main(String[] args) {
   		new FormattedTextSpike().setVisible(true);
	}
	
}
