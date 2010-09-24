package com.jamesshore.finances.domain;

public class TaxRate {

	private double rate;
	
	public TaxRate(double rateAsPercentage) {
		this.rate = rateAsPercentage / 100.0;
	}
	
	public Dollars simpleTaxFor(Dollars amount) {
		return new Dollars((int)(rate * amount.toInt()));
	}

	public Dollars compoundTaxFor(Dollars amount) {
		int amountAsInt = amount.toInt();
		return new Dollars((int)((amountAsInt / (1 - rate)) - amountAsInt));
	}
	
	@Override
	public String toString() {
		return (rate * 100) + "%";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(rate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		TaxRate other = (TaxRate) obj;
		if (Double.doubleToLongBits(rate) != Double.doubleToLongBits(other.rate)) return false;
		return true;
	}

}
