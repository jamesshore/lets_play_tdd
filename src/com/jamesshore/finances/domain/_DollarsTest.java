package com.jamesshore.finances.domain;

import static org.junit.Assert.*;
import org.junit.*;


public class _DollarsTest {

	@Test
	public void addition() {
		assertEquals(new Dollars(40), new Dollars(10).plus(new Dollars(30)));
	}
	
	@Test
	public void subtraction() {
		assertEquals("positive result", new Dollars(20), new Dollars(50).minus(new Dollars(30)));
		assertEquals("negative result", new Dollars(-60), new Dollars(40).minus(new Dollars(100)));
	}
	
	@Test
	public void minusToZero() {
		assertEquals("positive result", new Dollars(20), new Dollars(50).subtractToZero(new Dollars(30)));
		assertEquals("no negative result--return zero instead", new Dollars(0), new Dollars(40).subtractToZero(new Dollars(100)));
	}

	@Test
	public void percentage() {
		assertEquals(new Dollars(20), new Dollars(100).percentage(20));
	}
	
	@Test
	public void min() {
		Dollars value1 = new Dollars(20);
		Dollars value2 = new Dollars(30);
		assertEquals("value 1", new Dollars(20), Dollars.min(value1, value2));
		assertEquals("value 2", new Dollars(20), Dollars.min(value2, value1));
	}
	
	@Test
	public void equalsIgnoresPennies() {
		assertTrue("should round down", new Dollars(10).equals(new Dollars(10.10)));
		assertTrue("should round up", new Dollars(10).equals(new Dollars(9.90)));
		assertTrue("should round up when we have exactly 50 cents", new Dollars(11).equals(new Dollars(10.5)));
	}
	
	@Test
	public void hashcodeIgnoresPenniesToo() {
		assertTrue("should round down", new Dollars(10).hashCode() == new Dollars(10.10).hashCode());
		assertTrue("should round up", new Dollars(10).hashCode() == new Dollars(9.90).hashCode());
		assertTrue("should round up when we have exactly 50 cents", new Dollars(11).hashCode() == new Dollars(10.5).hashCode());	
	}
	
	@Test
	public void toStringIgnoresPennies() {
		assertEquals("should round down", "$10", new Dollars(10.10).toString());
		assertEquals("should round up", "$10", new Dollars(9.90).toString());
		assertEquals("should round up when we have exactly 50 cents", "$11", new Dollars(10.5).toString());
	}
	
	@Test
	public void toStringFormatsLongNumbersWithCommas() {
		assertEquals("$1,234", new Dollars(1234).toString());
		assertEquals("$12,345,678", new Dollars(12345678).toString());
		assertEquals("$123,456,789", new Dollars(123456789).toString());
	}
	
	@Test
	public void valueObject() {
		Dollars dollars1a = new Dollars(10);
		Dollars dollars1b = new Dollars(10);
		Dollars dollars2 = new Dollars(20);
		
		assertEquals("$10", dollars1a.toString());
		assertTrue("dollars with same amount should be equal", dollars1a.equals(dollars1b));
		assertFalse("dollars with different amounts should not be equal", dollars1a.equals(dollars2));
		assertTrue("equal dollars should have same hash code", dollars1a.hashCode() == dollars1b.hashCode());
	}

}
