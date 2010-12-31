package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import javax.swing.*;
import org.junit.*;
import com.jamesshore.finances.domain.*;


public class _DollarsTextFieldTest {

	@Test
	public void canRetrieveAmount() {
		DollarsTextField field = new DollarsTextField(new Dollars(42));
		assertEquals(new Dollars(42), field.getDollars());
	}
	
	@Test
	public void internals_shouldThisBeDeleted() {
		DollarsTextField field = new DollarsTextField(new Dollars(42));
		assertTrue("component should be a JFormattedTextField", field instanceof JFormattedTextField);
	}
	
}
