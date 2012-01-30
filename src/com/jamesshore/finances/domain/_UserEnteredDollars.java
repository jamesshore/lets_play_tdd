package com.jamesshore.finances.domain;

import static org.junit.Assert.*;
import org.junit.*;

public class _UserEnteredDollars {

	// @Test
	// public void parseNumbersAndDollarsAndNegativeSigns() {
	// assertEquals("empty string", ValidDollars.create(0), new UserEnteredDollars(""));
	// assertEquals("just a number", ValidDollars.create(42), new UserEnteredDollars("42"));
	// assertEquals("beginning dollar sign", ValidDollars.create(42), new UserEnteredDollars("$42"));
	// assertEquals("dollar sign only", ValidDollars.create(0), new UserEnteredDollars("$"));
	// assertEquals("decimals", ValidDollars.create(42.13), new UserEnteredDollars("42.13"));
	// assertEquals("one comma", ValidDollars.create(1234), new UserEnteredDollars("1,234"));
	// assertEquals("several commas", ValidDollars.create(1234567), new UserEnteredDollars("1,234,567"));
	// assertEquals("dysfunctional commas", ValidDollars.create(42), new UserEnteredDollars(",,,4,,,,,,2,,,"));
	// assertEquals("negative number", ValidDollars.create(-42), new UserEnteredDollars("-42"));
	// assertEquals("negative dollars", ValidDollars.create(-42), new UserEnteredDollars("-$42"));
	// assertEquals("dollars negative", ValidDollars.create(-42), new UserEnteredDollars("$-42"));
	// assertEquals("negative sign only", ValidDollars.create(0), new UserEnteredDollars("-"));
	// assertEquals("negative and dollar sign only", ValidDollars.create(0), new UserEnteredDollars("-$"));
	// assertEquals("dollar and negative sign only", ValidDollars.create(0), new UserEnteredDollars("$-"));
	// }

	// @Test
	// public void parseParentheses() {
	// assertEquals("open parenthesis only", ValidDollars.create(0), new UserEnteredDollars("("));
	// assertEquals("close parenthesis only", new InvalidDollars(), new UserEnteredDollars(")"));
	// assertEquals("both parenthesis only", ValidDollars.create(0), new UserEnteredDollars("()"));
	// assertEquals("number in parentheses", ValidDollars.create(-42), new UserEnteredDollars("(42)"));
	// assertEquals("open parenthesis and number", ValidDollars.create(-42), new UserEnteredDollars("(42"));
	// assertEquals("close parenthesis and number", ValidDollars.create(-42), new UserEnteredDollars("42)"));
	// }
	//
	// @Test
	// public void parseIllegals() {
	// InvalidDollars invalid = new InvalidDollars();
	// assertEquals(invalid, new UserEnteredDollars("x"));
	// assertEquals(invalid, new UserEnteredDollars("40d"));
	// assertEquals(invalid, new UserEnteredDollars("40f"));
	// assertEquals(invalid, new UserEnteredDollars("NaN"));
	// }
	//
	// @Test
	// // This test handles the special case where the core Java library hangs when
	// // parsing a magic number
	// public void parsingTheDoubleOfDeathDoesntHangMachine() {
	// new UserEnteredDollars("2.2250738585072012e-308");
	// // should not hang -- if we reached this line, everything is okay.
	// }

	@Test
	public void valueObject() {
		UserEnteredDollars dollars1a = new UserEnteredDollars("1");
		UserEnteredDollars dollars1b = new UserEnteredDollars("1");
		UserEnteredDollars dollars1c = new UserEnteredDollars(" 1 ");
		UserEnteredDollars dollars2 = new UserEnteredDollars("2");

		assertEquals("$1", dollars1a.toString());

		assertTrue("dollars with same string should be equal", dollars1a.equals(dollars1b));
		assertTrue("dollars with different string but same value should be equal", dollars1a.equals(dollars1c));

		assertTrue("user-entered dollars should be comparable to valid dollars", dollars1c.equals(ValidDollars.create(1)));

		// assertTrue("dollars with same amount should be equal", dollars1a.equals(dollars1b));
		// assertFalse("dollars with different amounts should not be equal", dollars1a.equals(dollars2));
		// assertFalse("valid dollars aren't equal to invalid dollars", dollars1a.equals(new InvalidDollars()));
		// assertTrue("equal dollars should have same hash code", dollars1a.hashCode() == dollars1b.hashCode());
		// assertFalse("shouldn't blow up when comparing to null", dollars1a.equals(null));
	}
}
