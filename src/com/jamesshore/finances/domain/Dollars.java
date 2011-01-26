package com.jamesshore.finances.domain;

import java.text.*;
import java.util.*;

public class Dollars {

	private double amount;

	public static Dollars parse(String text) {
		if (text.startsWith("(")) text = text.substring(1);
		if (text.endsWith(")")) text = text.substring(0, text.length() - 1);
		if (text.startsWith("$")) text = text.substring(1);
		if (text.startsWith("-$")) text = "-" + text.substring(2);
		if (text.isEmpty()) return new Dollars(0);
		if (text.equals("-")) return new Dollars(0);
		text = text.replace(",", "");		
		
		return new Dollars(Double.parseDouble(text));
	}
	
	public Dollars(int amount) {
		this.amount = amount;
	}
	
	public Dollars(double amount) {
		this.amount = amount;
	}
	
	public Dollars plus(Dollars dollars) {
		return new Dollars(this.amount + dollars.amount);
	}

	public Dollars minus(Dollars dollars) {
		return new Dollars(this.amount - dollars.amount);
	}

	public Dollars subtractToZero(Dollars dollars) {
		double result = this.amount - dollars.amount;
		return new Dollars(Math.max(0, result));
	}

	public Dollars percentage(double percent) {
		return new Dollars(amount * percent / 100.0);
	}

	public static Dollars min(Dollars value1, Dollars value2) {
		return new Dollars(Math.min(value1.amount, value2.amount));
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
			return "(" + convertNumberToString() + ")";
		}
		else {
			return convertNumberToString();
		}
	}

	private String convertNumberToString() {
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
		Dollars that = (Dollars)obj;
		return this.roundOffPennies() == that.roundOffPennies();
	}
	
}
