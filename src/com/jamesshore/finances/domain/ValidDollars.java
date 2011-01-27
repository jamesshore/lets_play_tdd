package com.jamesshore.finances.domain;

import java.text.*;
import java.util.*;

public class ValidDollars extends Dollars{

	private double amount;

	public ValidDollars(int amount) {
		this.amount = amount;
	}
	
	public ValidDollars(double amount) {
		this.amount = amount;
	}
	
	private double amount(Dollars dollars) {
		return ((ValidDollars)dollars).amount;
	}
	
	public Dollars plus(Dollars dollars) {
		return new ValidDollars(this.amount + amount(dollars));
	}
	
	public Dollars minus(Dollars dollars) {
		return new ValidDollars(this.amount - amount(dollars));
	}

	public Dollars subtractToZero(Dollars dollars) {
		double result = this.amount - amount(dollars);
		return new ValidDollars(Math.max(0, result));
	}

	public Dollars percentage(double percent) {
		return new ValidDollars(amount * percent / 100.0);
	}
	
	public Dollars min(Dollars value2) {
		return new ValidDollars(Math.min(this.amount, amount(value2)));
	}

	private boolean isNegative() {
		return amount < 0;
	}
	
	private long roundOffPennies() {
		return Math.round(this.amount);
	}

	@Override
	public String toString() {
		if (isNegative()) {
			return "(" + toAbsoluteValueString() + ")";
		}
		else {
			return toAbsoluteValueString();
		}
	}

	private String toAbsoluteValueString() {
		long roundedAmount = roundOffPennies();
		roundedAmount = Math.abs(roundedAmount);
		return "$" + NumberFormat.getInstance(Locale.US).format(roundedAmount);
	}

	@Override
	public int hashCode() {
		return (int)roundOffPennies();
	}

	@Override
	public boolean equals(Object obj) {
		ValidDollars that = (ValidDollars)obj;
		return this.roundOffPennies() == that.roundOffPennies();
	}
}
