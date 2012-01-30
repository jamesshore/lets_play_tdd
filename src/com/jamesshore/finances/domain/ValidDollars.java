package com.jamesshore.finances.domain;

import java.awt.*;
import java.text.*;
import java.util.*;
import com.jamesshore.finances.ui.*;

public class ValidDollars extends Dollars {

	public static final double MAX_VALUE = 1000000000d; // one beeeellion dollars!
	public static final double MIN_VALUE = -1000000000d;

	private double amount;

	public static Dollars create(double amount) {
		if (inRange(amount)) return new ValidDollars(amount);
		else return new InvalidDollars();
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

	private static boolean inRange(double value) {
		return (value >= MIN_VALUE) && (value <= MAX_VALUE);
	}

	public Dollars plus(Dollars dollars) {
		if (!dollars.isValid()) return new InvalidDollars();
		if (dollars instanceof UserEnteredDollars) return dollars.plus(this);
		return create(this.amount + amount(dollars));
	}

	public Dollars minus(Dollars dollars) {
		if (!dollars.isValid()) return new InvalidDollars();
		return create(this.amount - amount(dollars));
	}

	public Dollars subtractToZero(Dollars dollars) {
		if (!dollars.isValid()) return new InvalidDollars();
		double result = this.amount - amount(dollars);
		return create(Math.max(0, result));
	}

	public Dollars percentage(double percent) {
		return create(amount * percent / 100.0);
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

	public void render(Resources resources, RenderTarget target) {
		target.setText(this.toString());
		target.setIcon(null, null);
		target.setForegroundColor(Color.BLACK);
		if (amount < 0) target.setForegroundColor(Color.RED);
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
		if (obj instanceof UserEnteredDollars) return obj.equals(this);

		ValidDollars that = (ValidDollars)obj;
		return this.roundOffPennies() == that.roundOffPennies();
	}
}
