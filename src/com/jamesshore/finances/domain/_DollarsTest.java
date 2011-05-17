package com.jamesshore.finances.domain;

import static org.junit.Assert.*;
import org.junit.*;

public class _DollarsTest {

	@Test
	public void parseNumbersAndDollarsAndNegativeSigns() {
		assertEquals("empty string", ValidDollars.create(0), Dollars.parse(""));
		assertEquals("just a number", ValidDollars.create(42), Dollars.parse("42"));
		assertEquals("beginning dollar sign", ValidDollars.create(42), Dollars.parse("$42"));
		assertEquals("dollar sign only", ValidDollars.create(0), Dollars.parse("$"));
		assertEquals("decimals", ValidDollars.create(42.13), Dollars.parse("42.13"));
		assertEquals("one comma", ValidDollars.create(1234), Dollars.parse("1,234"));
		assertEquals("several commas", ValidDollars.create(1234567), Dollars.parse("1,234,567"));
		assertEquals("dysfunctional commas", ValidDollars.create(42), Dollars.parse(",,,4,,,,,,2,,,"));
		assertEquals("negative number", ValidDollars.create(-42), Dollars.parse("-42"));
		assertEquals("negative dollars", ValidDollars.create(-42), Dollars.parse("-$42"));
		assertEquals("dollars negative", ValidDollars.create(-42), Dollars.parse("$-42"));
		assertEquals("negative sign only", ValidDollars.create(0), Dollars.parse("-"));
		assertEquals("negative and dollar sign only", ValidDollars.create(0), Dollars.parse("-$"));
		assertEquals("dollar and negative sign only", ValidDollars.create(0), Dollars.parse("$-"));
	}

	@Test
	public void parseParentheses() {
		assertEquals("open parenthesis only", ValidDollars.create(0), Dollars.parse("("));
		assertEquals("close parenthesis only", new InvalidDollars(), Dollars.parse(")"));
		assertEquals("both parenthesis only", ValidDollars.create(0), Dollars.parse("()"));
		assertEquals("number in parentheses", ValidDollars.create(-42), Dollars.parse("(42)"));
		assertEquals("open parenthesis and number", ValidDollars.create(-42), Dollars.parse("(42"));
		assertEquals("close parenthesis and number", ValidDollars.create(-42), Dollars.parse("42)"));
	}

	@Test
	public void parseIllegals() {
		InvalidDollars invalid = new InvalidDollars();
		assertEquals(invalid, Dollars.parse("x"));
		assertEquals(invalid, Dollars.parse("40d"));
		assertEquals(invalid, Dollars.parse("40f"));
		assertEquals(invalid, Dollars.parse("NaN"));
	}

	@Test
	// This test handles the special case where the core Java library hangs when
	// parsing a magic number
	public void parsingTheDoubleOfDeathDoesntHangMachine() {
		Dollars.parse("2.2250738585072012e-308");
		// should not hang -- if we reached this line, everything is okay.
	}

}
