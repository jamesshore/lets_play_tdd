package com.jamesshore.finances.domain;

import java.awt.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import com.jamesshore.finances.ui.*;

public class ValidDollars extends Dollars {

	public static final double MAX_VALUE = 1000000000d; // one beeeellion dollars!
	public static final double MIN_VALUE = -1000000000d;

	private double amount;

	public static Dollars create(double amount) {
		if (outOfRange(amount)) return new InvalidDollars();
		else return new ValidDollars(amount);
	}

	private ValidDollars(double amount) {
		this.amount = amount;
	}

	public boolean isValid() {
		return true;
	}

	private double amount(Dollars dollars) {
		return ((ValidDollars)dollars).amount;
	}

	private static boolean outOfRange(double value) {
		return (value > MAX_VALUE) || (value < MIN_VALUE);
	}

	public Dollars plus(Dollars dollars) {
		if (!dollars.isValid()) return new InvalidDollars();
		double result = this.amount + amount(dollars);
		if (outOfRange(result)) return new InvalidDollars();
		return create(result);
	}

	public Dollars minus(Dollars dollars) {
		if (!dollars.isValid()) return new InvalidDollars();
		double result = this.amount - amount(dollars);
		if (outOfRange(result)) return new InvalidDollars();
		return create(result);
	}

	public Dollars subtractToZero(Dollars dollars) {
		if (!dollars.isValid()) return new InvalidDollars();
		double result = this.amount - amount(dollars);
		if (outOfRange(result)) return new InvalidDollars();
		return create(Math.max(0, result));
	}

	public Dollars percentage(double percent) {
		double result = amount * percent / 100.0;
		if (outOfRange(result)) return new InvalidDollars();
		return create(result);
	}

	public Dollars min(Dollars value2) {
		if (!value2.isValid()) return new InvalidDollars();
		return create(Math.min(this.amount, amount(value2)));
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
