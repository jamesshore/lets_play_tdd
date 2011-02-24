package com.jamesshore.spikes.icon_in_table;

public class SpikeDollars {

	public double amount;

	public SpikeDollars(int amount) {
		this.amount = amount;
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
		return (int) roundOffPennies();
	}

	@Override
	public boolean equals(Object obj) {
		SpikeDollars that = (SpikeDollars) obj;
		return this.roundOffPennies() == that.roundOffPennies();
	}

}
