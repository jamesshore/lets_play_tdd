package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.awt.*;
import java.lang.reflect.*;
import javax.swing.*;
import org.junit.*;
import com.jamesshore.finances.domain.*;

public class _DollarsTextFieldTest {

	private DollarsTextField field;

	@Before
	public void setup() {
		field = new DollarsTextField(ValidDollars.create(42));
	}

	@Test
	public void canRetrieveAmount() {
		assertEquals(ValidDollars.create(42), field.getDollars());
	}

	@Test
	public void textReflectsDollarAmountUponConstruction() {
		assertEquals("$42", field.getText());
	}

	@Test
	public void changingTextChangesDollarAmount() {
		field.setText("1024");
		assertEquals(ValidDollars.create(1024), field.getDollars());
	}

	@Test
	public void fieldIsRenderedByDomainClassWhenTextChanges_EvenIfItHasntLostFocus() throws Exception {
		// TODO: decouple this from specifics of the colors used by domain class?

		field.setText("10");
		assertEquals("starts black", Color.BLACK, field.getForeground());
		field.setText("  -10 ");
		assertEquals("should change to red", Color.RED, field.getForeground());
		assertEquals("should not change text", "  -10 ", getTextUsingEventThread(field));
	}

	private String getTextUsingEventThread(DollarsTextField textField) throws InterruptedException, InvocationTargetException {
		final String[] testResult = { null };
		final DollarsTextField field = textField;
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				testResult[0] = (field.getText());
			}
		});
		return testResult[0];
	}
}
