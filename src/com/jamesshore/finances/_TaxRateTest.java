package com.jamesshore.finances;

import static org.junit.Assert.*;
import org.junit.*;


public class _TaxRateTest {

	@Test
	public void nothing() {
		TaxRate taxRate = new TaxRate(0);
		assertEquals(0, taxRate.taxFor(1000));
	}
	
	@Test
	public void simpleTax() {
		TaxRate taxRate = new TaxRate(25);
		assertEquals(250, taxRate.taxFor(1000));
	}
	
}
