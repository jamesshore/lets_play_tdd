package com.jamesshore.finances.domain;

import com.jamesshore.finances.util.*;

public class InterestRate {

	private double rateAsPercentage;
	
	public InterestRate(double rateAsPercentage) {
		Require.that(rateAsPercentage > 0, "interest rate must be positive (and not zero); was " + rateAsPercentage);
		this.rateAsPercentage = rateAsPercentage;
	}

	public Dollars interestOn(Dollars amount) {
		return amount.percentage(rateAsPercentage);
	}
	
	@Override
	public String toString() {
		return rateAsPercentage + "%";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(rateAsPercentage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		InterestRate other = (InterestRate) obj;
		if (Double.doubleToLongBits(rateAsPercentage) != Double.doubleToLongBits(other.rateAsPercentage)) return false;
		return true;
	}

}
