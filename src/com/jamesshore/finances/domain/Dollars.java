package com.jamesshore.finances.domain;

public abstract class Dollars implements SelfRenderable {

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
