package com.jamesshore.spikes.formatted_text;

public class SpikeDollars {

	private double amount;

	public SpikeDollars(int amount) {
		this.amount = amount;
	}
	
	public SpikeDollars(double amount) {
		this.amount = amount;
	}
		
	public SpikeDollars plus(SpikeDollars dollars) {
		return new SpikeDollars(this.amount + dollars.amount);
	}

	public SpikeDollars minus(SpikeDollars dollars) {
		return new SpikeDollars(this.amount - dollars.amount);
	}

	public SpikeDollars subtractToZero(SpikeDollars dollars) {
		double result = this.amount - dollars.amount;
		return new SpikeDollars(Math.max(0, result));
	}

	public SpikeDollars percentage(double percent) {
		return new SpikeDollars(amount * percent / 100.0);
	}

	public static SpikeDollars min(SpikeDollars value1, SpikeDollars value2) {
		return new SpikeDollars(Math.min(value1.amount, value2.amount));
	}

	private long roundOffPennies() {
		return Math.round(this.amount);
	}

	@Override
	public String toString() {
		return "$" + roundOffPennies() + " DOLLARS";
	}

	@Override
	public int hashCode() {
		return (int)roundOffPennies();
	}

	@Override
	public boolean equals(Object obj) {
		SpikeDollars that = (SpikeDollars)obj;
		return this.roundOffPennies() == that.roundOffPennies();
	}
	
}
