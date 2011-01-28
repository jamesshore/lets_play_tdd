package com.jamesshore.finances.domain;


public abstract class Dollars {

	public static Dollars parse(String text) {
		boolean parenthesis = false;
		if (text.startsWith("(")) { text = text.substring(1); parenthesis = true; }
		if (text.endsWith(")")) { text = text.substring(0, text.length() - 1); parenthesis = true; }
		if (parenthesis) text = "-" + text;
		
		if (text.startsWith("$")) text = text.substring(1);
		if (text.startsWith("-$")) text = "-" + text.substring(2);
		if (text.isEmpty()) return new ValidDollars(0);
		if (text.equals("-")) return new ValidDollars(0);
		text = text.replace(",", "");		
		
		return new ValidDollars(Double.parseDouble(text));
	}
	
	public static Dollars min(Dollars value1, Dollars value2) {
		return value1.min(value2);
	}

	public abstract boolean isValid();
	public abstract Dollars plus(Dollars dollars);
	public abstract Dollars minus(Dollars dollars);
	public abstract Dollars subtractToZero(Dollars dollars);
	public abstract Dollars percentage(double percent);
	public abstract Dollars min(Dollars value2);
	
}
