package com.jamesshore.finances.util;

import static org.junit.Assert.*;
import org.junit.*;


public class _RequireTest {

	@Test
	public void that() {
		try {
			Require.that(false, "some message");
			fail("expected exception");
		}
		catch (RequireException e) {
			assertEquals("some message", e.getMessage());
		}
	}

}

class RequireException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
}