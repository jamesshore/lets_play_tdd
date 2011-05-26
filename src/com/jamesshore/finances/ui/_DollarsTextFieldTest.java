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
	public void textReflectsDollarAmountUponConstruction() {
		assertEquals("$42", field.getText());
	}

	@Test
	public void canRetrieveAmount() {
		assertEquals(ValidDollars.create(42), field.getDollars());
	}

	@Test
	public void changingTextChangesDollarAmount() {
		field.setText("1024");
		assertEquals(ValidDollars.create(1024), field.getDollars());
	}

	@Test
	public void canCallFunctionWhenTextChanges() {
		final boolean[] changed = { false };
		DollarsTextField.ChangeListener listener = new DollarsTextField.ChangeListener() {
			public void textChanged() {
				changed[0] = true;
			}
		};

		field.addTextChangeListener(listener);
		assertFalse("textChanged() should not have been called yet", changed[0]);
		field.setText("1000");
		assertTrue("textChanged() should have been called", changed[0]);
	}

	@Test
	public void fieldIsRenderedByDomainClassWhenTextChanges_EvenIfItHasntLostFocus() throws Exception {
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
