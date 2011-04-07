package com.jamesshore.finances.domain;

import static org.junit.Assert.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import org.junit.*;
import com.jamesshore.finances.ui.*;

public class _ValidDollarsTest {

	private Dollars twentyDollars;
	private Dollars MAX_VALID = ValidDollars.create(ValidDollars.MAX_VALUE);
	private Dollars MIN_VALID = ValidDollars.create(ValidDollars.MIN_VALUE);

	@Before
	public void setup() {
		twentyDollars = ValidDollars.create(20);
	}

	@Test
	public void cannotConstructDollarsLargerThanMaxRange() {
		assertEquals("overflow", new InvalidDollars(), ValidDollars.create(ValidDollars.MAX_VALUE + 1));
		assertEquals("underflow", new InvalidDollars(), ValidDollars.create(ValidDollars.MIN_VALUE - 1));
	}

	@Test
	public void isInvalid() {
		assertTrue(ValidDollars.create(42).isValid());
	}

	@Test
	public void addition() {
		assertEquals("addition", ValidDollars.create(40), ValidDollars.create(10).plus(ValidDollars.create(30)));
		assertEquals("overflow", new InvalidDollars(), MAX_VALID.plus(ValidDollars.create(1)));
		assertEquals("underflow", new InvalidDollars(), MIN_VALID.plus(ValidDollars.create(-1)));
	}

	@Test
	public void subtraction() {
		assertEquals("positive result", ValidDollars.create(20), ValidDollars.create(50).minus(ValidDollars.create(30)));
		assertEquals("negative result", ValidDollars.create(-60), ValidDollars.create(40).minus(ValidDollars.create(100)));
		assertEquals("overflow", new InvalidDollars(), MAX_VALID.minus(ValidDollars.create(-1)));
		assertEquals("underflow", new InvalidDollars(), MIN_VALID.minus(ValidDollars.create(1)));
	}

	@Test
	public void minusToZero() {
		assertEquals("positive result", ValidDollars.create(20), ValidDollars.create(50).subtractToZero(ValidDollars.create(30)));
		assertEquals("no negative result--return zero instead", ValidDollars.create(0), ValidDollars.create(40).subtractToZero(ValidDollars.create(100)));
		assertEquals("overflow", new InvalidDollars(), MAX_VALID.subtractToZero(ValidDollars.create(-1)));
	}

	@Test
	public void percentage() {
		assertEquals("percent", ValidDollars.create(20), ValidDollars.create(100).percentage(20));
		assertEquals("overflow", new InvalidDollars(), MAX_VALID.percentage(200));
	}

	@Test
	public void min() {
		Dollars value1 = ValidDollars.create(20);
		Dollars value2 = ValidDollars.create(30);
		assertEquals("value 1", ValidDollars.create(20), Dollars.min(value1, value2));
		assertEquals("value 2", ValidDollars.create(20), Dollars.min(value2, value1));
	}

	@Test
	public void renderToSwingLabel() {
		JLabel label = new JLabel();
		twentyDollars.render(new Resources(), label);
		assertEquals("label text should be toString() value", twentyDollars.toString(), label.getText());
	}

	@Test
	public void renderNegativeValuesInRed() {
		JLabel label = new JLabel();
		Dollars minusTwenty = ValidDollars.create(-20);
		minusTwenty.render(new Resources(), label);
		assertEquals("red when negative", Color.RED, label.getForeground());
	}

	@Test
	public void renderZeroAndPositiveInBlack() {
		JLabel label = new JLabel();
		Dollars zero = ValidDollars.create(0);
		zero.render(new Resources(), label);
		assertEquals("black when zero", Color.BLACK, label.getForeground());

		label = new JLabel();
		twentyDollars.render(new Resources(), label);
		assertEquals("black when positive", Color.BLACK, label.getForeground());
	}

	@Test
	public void renderingShouldResetLabelToDefaultState() {
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon());
		label.setToolTipText("bogus tooltip");
		label.setForeground(Color.CYAN);

		twentyDollars.render(new Resources(), label);
		assertNull("should not have icon", label.getIcon());
		assertNull("should not have tooltip", label.getToolTipText());
		assertEquals("foreground color", Color.BLACK, label.getForeground());
	}

	@Test
	public void equalsIgnoresPennies() {
		assertTrue("should round down", ValidDollars.create(10).equals(ValidDollars.create(10.10)));
		assertTrue("should round up", ValidDollars.create(10).equals(ValidDollars.create(9.90)));
		assertTrue("should round up when we have exactly 50 cents", ValidDollars.create(11).equals(ValidDollars.create(10.5)));
	}

	@Test
	public void hashcodeIgnoresPenniesToo() {
		assertTrue("should round down", ValidDollars.create(10).hashCode() == ValidDollars.create(10.10).hashCode());
		assertTrue("should round up", ValidDollars.create(10).hashCode() == ValidDollars.create(9.90).hashCode());
		assertTrue("should round up when we have exactly 50 cents", ValidDollars.create(11).hashCode() == ValidDollars.create(10.5).hashCode());
	}

	@Test
	public void toStringIgnoresPennies() {
		assertEquals("should round down", "$10", ValidDollars.create(10.10).toString());
		assertEquals("should round up", "$10", ValidDollars.create(9.90).toString());
		assertEquals("should round up when we have exactly 50 cents", "$11", ValidDollars.create(10.5).toString());
	}

	@Test
	public void toStringFormatsLongNumbersWithCommas() {
		assertEquals("$1,234", ValidDollars.create(1234).toString());
		assertEquals("$12,345,678", ValidDollars.create(12345678).toString());
		assertEquals("$123,456,789", ValidDollars.create(123456789).toString());
	}

	@Test
	public void toStringFormatsNegativeNumbersWithParentheses() {
		assertEquals("($500)", ValidDollars.create(-500).toString());
	}

	@Test
	public void toStringFormatsInTheUsaStyleEvenWhenInDifferentLocales() {
		try {
			Locale.setDefault(Locale.FRANCE);
			assertEquals("$1,234", ValidDollars.create(1234).toString());
		}
		finally {
			Locale.setDefault(Locale.US);
		}
	}

	@Test
	public void valueObject() {
		Dollars dollars1a = ValidDollars.create(10);
		Dollars dollars1b = ValidDollars.create(10);
		Dollars dollars2 = ValidDollars.create(20);

		assertEquals("$10", dollars1a.toString());
		assertTrue("dollars with same amount should be equal", dollars1a.equals(dollars1b));
		assertFalse("dollars with different amounts should not be equal", dollars1a.equals(dollars2));
		assertFalse("valid dollars aren't equal to invalid dollars", dollars1a.equals(new InvalidDollars()));
		assertTrue("equal dollars should have same hash code", dollars1a.hashCode() == dollars1b.hashCode());
		assertFalse("shouldn't blow up when comparing to null", dollars1a.equals(null));
	}

}
