package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.awt.event.*;
import javax.swing.*;
import org.junit.*;
import com.jamesshore.finances.domain.*;


public class _DollarsTextFieldTest {

	private DollarsTextField field;

	@Before
	public void setup() {
		field = new DollarsTextField(new Dollars(42));
	}
	
	@Test
	public void canRetrieveAmount() {
		assertEquals(new Dollars(42), field.getDollars());
	}

	@Test
	public void textReflectsDollarAmountUponConstruction() {
		assertEquals("$42", field.getText());
	}
	
	@Test
	public void changingTextChangesDollarAmount() {
		field.setText("1024");
		assertEquals(new Dollars(1024), field.getDollars());
	}
	
	@Test
	public void textMayStartWithDollarSign() {
		field.setText("$42");
		assertEquals(new Dollars(42), field.getDollars());
		field.setText("$");
		assertEquals(new Dollars(0), field.getDollars());
	}
	
	@Test
	public void emptyStringIsZeroDollars() {
		field.setText("");
		assertEquals(new Dollars(0), field.getDollars());
	}
	
	@Test
	public void decimalsWork() {
		field.setText("1.23");
		assertEquals(new Dollars(1.23), field.getDollars());
	}
	
	@Test
	public void commasAreOkay() {
		field.setText("1,234");
		assertEquals(new Dollars(1234), field.getDollars());
		field.setText("1,234,567");
		assertEquals(new Dollars(1234567), field.getDollars());
		field.setText("1,,,,,2");
		assertEquals(new Dollars(12), field.getDollars());
	}
	
	@Test
	public void negatives() {
		field.setText("-9.32");
		assertEquals(new Dollars(-9.32), field.getDollars());
		field.setText("-");
		assertEquals(new Dollars(0), field.getDollars());
	}
	
	@Test
	public void fieldIsReformattedWhenItLosesFocus() throws Exception {
		field.dispatchEvent(new FocusEvent(field, FocusEvent.FOCUS_GAINED));
		field.setText("10");
		field.dispatchEvent(new FocusEvent(field, FocusEvent.FOCUS_LOST));
		
		final String[] testResult = {null};
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				testResult[0] = (field.getText());
			}
		});
		assertEquals("$10", testResult[0]);
	}

}
