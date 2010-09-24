package com.jamesshore.finances.util;

public class Require {

	public static void that(boolean expression, String message) {
		if (!expression) throw new RequireException(message);
		
	}

}

class RequireException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RequireException(String message) {
		super(message);
	}
	
}