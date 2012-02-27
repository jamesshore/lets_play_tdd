package com.jamesshore.finances.values;

import com.jamesshore.finances.domain.*;

public abstract class Dollars implements SelfRenderable {

	static final double MAX_VALUE = 1000000000d; // one beeeellion dollars!
	static final double MIN_VALUE = -1000000000d;

	protected static Dollars create(double amount) {
		if (inRange(amount)) return new ValidDollars(amount);
		else return new InvalidDollars();
	}

	protected static boolean inRange(double value) {
		return (value >= MIN_VALUE) && (value <= MAX_VALUE);
	}

	public static Dollars min(Dollars value1, Dollars value2) {
		return value1.min(value2);
	}

	public Dollars flipSign() {
		return new ValidDollars(0).minus(this);
	}

	public abstract boolean isValid();

	protected abstract double toCoreDataType();

	public abstract Dollars plus(Dollars operand);

	public abstract Dollars minus(Dollars operand);

	public abstract Dollars subtractToZero(Dollars operand);

	public abstract Dollars percentage(double percent);

	public abstract Dollars min(Dollars operand);

}
