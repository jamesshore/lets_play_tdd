package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
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
}
