package com.jamesshore.finances.domain;

public class Dollars {

	private double amount;

	public Dollars(int amount) {
		this.amount = amount;
	}
	
	public Dollars(double amount) {
		this.amount = amount;
	}
	
	public Dollars add(Dollars dollars) {
		return new Dollars(this.amount + dollars.amount);
	}

	public Dollars subtract(Dollars dollars) {
		return new Dollars(this.amount - dollars.amount);
	}

	public Dollars subtractToZero(Dollars dollars) {
		double result = this.amount - dollars.amount;
		return new Dollars(Math.max(0, result));
	}

	public Dollars percentage(double percent) {
		return new Dollars(amount * percent / 100.0);
	}

	private long roundOffPennies() {
		return Math.round(this.amount);
	}

	@Override
	public String toString() {
		return "$" + roundOffPennies();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Dollars that = (Dollars)obj;
		return this.roundOffPennies() == that.roundOffPennies();
	}
	
}
