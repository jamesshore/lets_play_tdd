package com.jamesshore.finances;

import java.math.*;

public class TaxRate {

	private BigDecimal rate;
	
	public TaxRate(double rateAsPercentage) {
		this.rate = new BigDecimal(rateAsPercentage).divide(new BigDecimal(100));
	}

	public int taxFor(int amount) {
		return (int)(amount. * rate);
	}

}
