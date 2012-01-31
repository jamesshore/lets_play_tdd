package com.jamesshore.finances.util;

public class Require {

	public static void that(boolean expression, String message) {
		if (!expression) throw new RequireException(message);
		
	}

}