package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import org.junit.*;


public class _ApplicationFrameTest {

	@Test
	public void applicationWindowShouldHaveTitle() {
		ApplicationFrame frame = new ApplicationFrame();
		assertEquals("title", ApplicationFrame.TITLE, frame.getTitle());
	}
	
}
