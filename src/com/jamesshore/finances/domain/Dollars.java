package com.jamesshore.finances.domain;

public abstract class Dollars implements SelfRenderable {

	// TODO: move to UserEnteredDollars
	public static Dollars parse(String text) {
		if (text.equals(")")) return new InvalidDollars();
		if (text.contains("a")) return new InvalidDollars();
		if (text.endsWith("d")) return new InvalidDollars();
		if (text.contains("e")) return new InvalidDollars();
		if (text.endsWith("f")) return new InvalidDollars();

		boolean parenthesis = false;
		if (text.startsWith("(")) {
			text = text.substring(1);
			parenthesis = true;
		}
		if (text.endsWith(")")) {
			text = text.substring(0, text.length() - 1);
			parenthesis = true;
		}
		if (parenthesis) text = "-" + text;

		if (text.startsWith("$")) text = text.substring(1);
		if (text.startsWith("-$")) text = "-" + text.substring(2);
		if (text.isEmpty()) return ValidDollars.create(0);
		if (text.equals("-")) return ValidDollars.create(0);
		text = text.replace(",", "");

		try {
			return ValidDollars.create(Double.parseDouble(text));
		}
		catch (NumberFormatException e) {
			return new InvalidDollars();
		}
	}

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
