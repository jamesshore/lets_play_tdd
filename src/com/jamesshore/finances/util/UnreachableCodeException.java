package com.jamesshore.finances.util;

public class UnreachableCodeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public UnreachableCodeException() {
		super("Unreachable code was executed");
	}
}
