package com.jamesshore.finances.domain;

import com.jamesshore.finances.ui.*;

public class UserEnteredDollars extends Dollars {

	private String userText;
	private Dollars backingDollars;

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

	public UserEnteredDollars(String userText) {
		this.userText = userText;
		this.backingDollars = this.parse2(userText); // TODO: move parse method into this class
	}

	private Dollars parse2(String text) {
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

	@Override
	public void render(Resources resources, RenderTarget target) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid() {
		return backingDollars.isValid();
	}

	@Override
	public Dollars plus(Dollars dollars) {
		if (dollars instanceof UserEnteredDollars) return backingDollars.plus(((UserEnteredDollars)dollars).backingDollars);
		else return backingDollars.plus(dollars);
	}

	@Override
	public Dollars minus(Dollars dollars) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dollars subtractToZero(Dollars dollars) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dollars percentage(double percent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dollars min(Dollars value2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return backingDollars.toString();
	}

	@Override
	public int hashCode() {
		return backingDollars.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;

		if (obj instanceof UserEnteredDollars) {
			UserEnteredDollars that = (UserEnteredDollars)obj;
			return this.backingDollars.equals(that.backingDollars);
		}
		else {
			Dollars that = (Dollars)obj;
			return this.backingDollars.equals(that);
		}
	}
}
