package com.jamesshore.finances.util;

public class RequireException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RequireException(String message) {
		super(message);
	}

}