package com.jamesshore.finances.domain;

public abstract class Dollars implements SelfRenderable {

	public static Dollars min(Dollars value1, Dollars value2) {
		return value1.min(value2);
	}

	public Dollars flipSign() {
		return ValidDollars.create(0).minus(this);
	}

	public abstract boolean isValid();

	public abstract Dollars plus(Dollars dollars);

	public abstract Dollars minus(Dollars dollars);

	public abstract Dollars subtractToZero(Dollars dollars);

	public abstract Dollars percentage(double percent);

	public abstract Dollars min(Dollars value2);
}
