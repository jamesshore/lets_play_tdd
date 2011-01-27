package com.jamesshore.spikes.formatted_text;

import java.awt.*;
import java.beans.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.event.*;
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
		final JFormattedTextField field = new JFormattedTextField(formatterFactory());
		field.setValue(new Long(30));
		field.addPropertyChangeListener("value", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println("propertyChange event fired");
				Long value = (Long)evt.getNewValue();
				SpikeDollars dollars = new SpikeDollars(value.doubleValue());
				button.setText(dollars.toString());
			}
		});
		
		field.getDocument().addDocumentListener(new DocumentListener() {
			@Override public void removeUpdate(DocumentEvent e) { updateApplicationModel(); }
			@Override public void insertUpdate(DocumentEvent e) { updateApplicationModel(); }
			@Override public void changedUpdate(DocumentEvent e) { updateApplicationModel(); }
			private void updateApplicationModel() {
				// NOTE: field.getValue() is not updated until the field loses focus
				// or the user presses enter. It does not update when the document changes.
				System.out.println("Document event fired");
				Long value = (Long)field.getValue();
				SpikeDollars dollars = new SpikeDollars(value.doubleValue());
				button.setText("*" + dollars.toString());
			}
		});
		
		return field;
	}
	
	private AbstractFormatterFactory formatterFactory() {
		NumberFormat displayFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE);
		displayFormat.setCurrency(Currency.getInstance(Locale.US));
		if (displayFormat instanceof DecimalFormat) {
			displayFormat.setMaximumFractionDigits(0);
		}
		
		AbstractFormatter displayFormatter = new NumberFormatter(displayFormat);
		AbstractFormatter editFormatter = new NumberFormatter(NumberFormat.getNumberInstance());
		
		DefaultFormatterFactory factory = new DefaultFormatterFactory(displayFormatter, displayFormatter, editFormatter);
		
		return factory;
	}

	public static void main(String[] args) {
   		new FormattedTextSpike().setVisible(true);
	}
	
}
