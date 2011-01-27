package com.jamesshore.finances.domain;

import static org.junit.Assert.*;
import org.junit.*;


public class _TaxRateTest {

	@Test
	public void simpleTaxJustAppliesTaxRateToAmount() {
		TaxRate taxRate = new TaxRate(25);
		assertEquals(new ValidDollars(250), taxRate.simpleTaxFor(new ValidDollars(1000)));
	}
	
	@Test
	public void compoundTaxIsTheAmountOfTaxThatIsIncurredIfYouAlsoPayTaxOnTheTax() {
		TaxRate taxRate = new TaxRate(25);
		assertEquals(new ValidDollars(333), taxRate.compoundTaxFor(new ValidDollars(1000)));
	}
	
	@Test
	public void valueObject() {
		TaxRate rate1a = new TaxRate(33);
		TaxRate rate1b = new TaxRate(33);
		TaxRate rate2 = new TaxRate(40);
		
		assertEquals("33.0%", rate1a.toString());
		assertTrue("same values should be equal", rate1a.equals(rate1b));
		assertFalse("different values should not be equal", rate1a.equals(rate2));
		assertTrue("same values have same hash code", rate1a.hashCode() == rate1b.hashCode());
	}
}
