package com.jamesshore.finances.values;

import com.jamesshore.finances.util.*;

public class GrowthRate {

	private double rateAsPercentage;
	
	public GrowthRate(double rateAsPercentage) {
		Require.that(rateAsPercentage > 0, "interest rate must be positive (and not zero); was " + rateAsPercentage);
		this.rateAsPercentage = rateAsPercentage;
	}

	public Dollars growthFor(Dollars amount) {
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
		GrowthRate other = (GrowthRate) obj;
		if (Double.doubleToLongBits(rateAsPercentage) != Double.doubleToLongBits(other.rateAsPercentage)) return false;
		return true;
	}

}
