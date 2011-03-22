package com.jamesshore.finances.domain;

import java.awt.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import com.jamesshore.finances.ui.*;

public class ValidDollars extends Dollars {

	private static final double MAX_DOUBLE = 1000000000d; // one beeeellion dollars!
	private static final double MIN_DOUBLE = MAX_DOUBLE * -1;
	public static final ValidDollars MAXIMUM_VALUE = new ValidDollars(MAX_DOUBLE);
	public static final ValidDollars MINIMUM_VALUE = new ValidDollars(MIN_DOUBLE);

	private double amount;

	public ValidDollars(int amount) {
		this.amount = amount;
	}

	public ValidDollars(double amount) {
		this.amount = amount;
	}

	public boolean isValid() {
		return true;
	}

	private double amount(Dollars dollars) {
		return ((ValidDollars)dollars).amount;
	}

	public Dollars plus(Dollars dollars) {
		if (!dollars.isValid()) return new InvalidDollars();
		double result = this.amount + amount(dollars);
		if (result > MAX_DOUBLE) return new InvalidDollars();
		if (result < MIN_DOUBLE) return new InvalidDollars();
		return new ValidDollars(result);
	}

	public Dollars minus(Dollars dollars) {
		if (!dollars.isValid()) return new InvalidDollars();
		return new ValidDollars(this.amount - amount(dollars));
	}

	public Dollars subtractToZero(Dollars dollars) {
		if (!dollars.isValid()) return new InvalidDollars();
		double result = this.amount - amount(dollars);
		return new ValidDollars(Math.max(0, result));
	}

	public Dollars percentage(double percent) {
		return new ValidDollars(amount * percent / 100.0);
	}

	public Dollars min(Dollars value2) {
		if (!value2.isValid()) return new InvalidDollars();
		return new ValidDollars(Math.min(this.amount, amount(value2)));
	}

	private boolean isNegative() {
		return amount < 0;
	}

	private long roundOffPennies() {
		return Math.round(this.amount);
	}

	public void render(Resources resources, JLabel label) {
		label.setIcon(null);
		label.setToolTipText(null);
		label.setText(this.toString());
		label.setForeground(Color.BLACK);
		if (amount < 0) label.setForeground(Color.RED);
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
		if (obj == null || obj instanceof InvalidDollars) return false;
		ValidDollars that = (ValidDollars)obj;
		return this.roundOffPennies() == that.roundOffPennies();
	}
}
