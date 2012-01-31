package com.jamesshore.finances.domain;

import static org.junit.Assert.*;
import org.junit.*;
import com.jamesshore.finances.ui.*;

public class _UserEnteredDollarsTest {

	private UserEnteredDollars dollars1a = new UserEnteredDollars("1");
	private UserEnteredDollars dollars1b = new UserEnteredDollars("1");
	private UserEnteredDollars dollars1Spaces = new UserEnteredDollars(" 1 ");
	private UserEnteredDollars dollars2 = new UserEnteredDollars("2");
	private UserEnteredDollars invalid = new UserEnteredDollars("xxx");

	@Test
	public void parseNumbersAndDollarsAndNegativeSigns() {
		assertEquals("empty string", ValidDollars.create(0), new UserEnteredDollars(""));
		assertEquals("just a number", ValidDollars.create(42), new UserEnteredDollars("42"));
		assertEquals("beginning dollar sign", ValidDollars.create(42), new UserEnteredDollars("$42"));
		assertEquals("dollar sign only", ValidDollars.create(0), new UserEnteredDollars("$"));
		assertEquals("decimals", ValidDollars.create(42.13), new UserEnteredDollars("42.13"));
		assertEquals("one comma", ValidDollars.create(1234), new UserEnteredDollars("1,234"));
		assertEquals("several commas", ValidDollars.create(1234567), new UserEnteredDollars("1,234,567"));
		assertEquals("dysfunctional commas", ValidDollars.create(42), new UserEnteredDollars(",,,4,,,,,,2,,,"));
		assertEquals("negative number", ValidDollars.create(-42), new UserEnteredDollars("-42"));
		assertEquals("negative dollars", ValidDollars.create(-42), new UserEnteredDollars("-$42"));
		assertEquals("dollars negative", ValidDollars.create(-42), new UserEnteredDollars("$-42"));
		assertEquals("negative sign only", ValidDollars.create(0), new UserEnteredDollars("-"));
		assertEquals("negative and dollar sign only", ValidDollars.create(0), new UserEnteredDollars("-$"));
		assertEquals("dollar and negative sign only", ValidDollars.create(0), new UserEnteredDollars("$-"));
	}

	@Test
	public void parseParentheses() {
		assertEquals("open parenthesis only", ValidDollars.create(0), new UserEnteredDollars("("));
		assertEquals("close parenthesis only", new InvalidDollars(), new UserEnteredDollars(")"));
		assertEquals("both parenthesis only", ValidDollars.create(0), new UserEnteredDollars("()"));
		assertEquals("number in parentheses", ValidDollars.create(-42), new UserEnteredDollars("(42)"));
		assertEquals("open parenthesis and number", ValidDollars.create(-42), new UserEnteredDollars("(42"));
		assertEquals("close parenthesis and number", ValidDollars.create(-42), new UserEnteredDollars("42)"));
	}

	@Test
	public void parseIllegals() {
		InvalidDollars invalid = new InvalidDollars();
		assertEquals(invalid, new UserEnteredDollars("x"));
		assertEquals(invalid, new UserEnteredDollars("40d"));
		assertEquals(invalid, new UserEnteredDollars("40f"));
		assertEquals(invalid, new UserEnteredDollars("NaN"));
	}

	@Test
	// This test handles the special case where the core Java library hangs when
	// parsing a magic number
	public void parsingTheDoubleOfDeathDoesntHangMachine() {
		new UserEnteredDollars("2.2250738585072012e-308");
		// should not hang -- if we reached this line, everything is okay.
	}

	@Test
	public void isValid() {
		assertTrue(dollars1a.isValid());
	}

	@Test
	public void toCoreDataType() {
		assertEquals(1.234, new UserEnteredDollars("1.234").toCoreDataType(), 0);
	}

	@Test
	public void rendersItself() {
		__RenderTargetStub target = new __RenderTargetStub();
		twentyDollars.render(new Resources(), target);
		assertEquals("label text should be toString() value", twentyDollars.toString(), target.text);
	}

	@Test
	public void plus() {
		assertEquals("should be able to add two user-entered dollars", ValidDollars.create(3), dollars1a.plus(dollars2));
		assertEquals("should be able to add user-entered dollars to valid dollars", ValidDollars.create(4), dollars1a.plus(ValidDollars.create(3)));
		assertEquals("should be able to add user-entered dollars to invalid dollars", new InvalidDollars(), dollars1a.plus(new InvalidDollars()));
		assertEquals("should be able to add valid dollars to user-entered dollars", ValidDollars.create(5), ValidDollars.create(4).plus(dollars1a));
		assertEquals("should be able to add invalid dollars to user-entered dollars", new InvalidDollars(), new InvalidDollars().plus(dollars1a));
	}

	@Test
	public void minus() {
		assertEquals("should to able to subtract two user-entered dollars", ValidDollars.create(-1), dollars1a.minus(dollars2));
		assertEquals("should be able to minus user-entered dollars to valid dollars", ValidDollars.create(-2), dollars1a.minus(ValidDollars.create(3)));
		assertEquals("should be able to minus user-entered dollars to invalid dollars", new InvalidDollars(), dollars1a.minus(new InvalidDollars()));
		assertEquals("should be able to minus valid dollars to user-entered dollars", ValidDollars.create(3), ValidDollars.create(4).minus(dollars1a));
		assertEquals("should be able to minus invalid dollars to user-entered dollars", new InvalidDollars(), new InvalidDollars().minus(dollars1a));
	}

	@Test
	public void subtractToZero() {
		assertEquals("should to able to subtract-to-zero two user-entered dollars", ValidDollars.create(0), dollars1a.subtractToZero(dollars2));
		assertEquals("should be able to subtract-to-zero user-entered dollars to valid dollars", ValidDollars.create(0), dollars1a.subtractToZero(ValidDollars.create(3)));
		assertEquals("should be able to subtract-to-zero user-entered dollars to invalid dollars", new InvalidDollars(), dollars1a.subtractToZero(new InvalidDollars()));
		assertEquals("should be able to subtract-to-zero valid dollars to user-entered dollars", ValidDollars.create(3), ValidDollars.create(4).subtractToZero(dollars1a));
		assertEquals("should be able to subtract-to-zero invalid dollars to user-entered dollars", new InvalidDollars(), new InvalidDollars().subtractToZero(dollars1a));
	}

	@Test
	public void percentage() {
		assertEquals("should to able to percentage user-entered dollars", ValidDollars.create(5), new UserEnteredDollars("50").percentage(10));
	}

	@Test
	public void min() {
		assertEquals("should to able to min two user-entered dollars", ValidDollars.create(1), dollars1a.min(dollars2));
		assertEquals("should be able to min user-entered dollars to valid dollars", ValidDollars.create(1), dollars1a.min(ValidDollars.create(3)));
		assertEquals("should be able to min user-entered dollars to invalid dollars", new InvalidDollars(), dollars1a.min(new InvalidDollars()));
		assertEquals("should be able to min valid dollars to user-entered dollars", ValidDollars.create(1), ValidDollars.create(4).min(dollars1a));
		assertEquals("should be able to min invalid dollars to user-entered dollars", new InvalidDollars(), new InvalidDollars().min(dollars1a));
	}

	@Test
	public void valueObject() {
		assertEquals("$1", dollars1a.toString());

		assertTrue("dollars with same string should be equal", dollars1a.equals(dollars1b));
		assertTrue("dollars with different string but same value should be equal", dollars1a.equals(dollars1Spaces));

		assertTrue("user-entered dollars should be comparable to valid dollars when equal", dollars1Spaces.equals(ValidDollars.create(1)));
		assertFalse("user-entered dollars should be comparable to valid dollars when unequal", dollars1Spaces.equals(ValidDollars.create(2)));

		assertFalse("user-entered dollars should be comparable to invalid dollars when valid", dollars1Spaces.equals(new InvalidDollars()));
		assertTrue("user-entered dollars should be comparable to invalid dollars when invalid", invalid.equals(new InvalidDollars()));

		assertTrue("equal dollars should have same hash code even if string is different", dollars1a.hashCode() == dollars1Spaces.hashCode());
		assertFalse("shouldn't blow up when comparing to null", dollars1a.equals(null));
	}
}
