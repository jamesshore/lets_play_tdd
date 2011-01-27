package com.jamesshore.finances.domain;

import static org.junit.Assert.*;
import org.junit.*;


public class _InvalidDollarsTest {

	@Test
	public void valueObject() {
		InvalidDollars dollars1a = new InvalidDollars();
		InvalidDollars dollars1b = new InvalidDollars();
		
		assertEquals("$???", dollars1a.toString());
//		assertTrue("invalid dollars are always equal", dollars1a.equals(dollars1b));
//		assertTrue("equal dollars should have same hash code", dollars1a.hashCode() == dollars1b.hashCode());

	}
	
//	@Test
//	public void plus() {
//		InvalidDollars a = new InvalidDollars();
//		InvalidDollars b = new InvalidDollars();
//		assertEquals(new InvalidDollars(), a.plus(b));
//	}
}
