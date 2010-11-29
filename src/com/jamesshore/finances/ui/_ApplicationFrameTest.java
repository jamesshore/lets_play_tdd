package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.awt.*;
import org.junit.*;


public class _ApplicationFrameTest {

	private ApplicationFrame frame;

	
	@Before
	public void setup() {
		frame = new ApplicationFrame();
	}
	
	@Test
	public void shouldHaveTitle() {
		assertEquals("title", ApplicationFrame.TITLE, frame.getTitle());
	}
	
	@Test
	public void shouldHaveHardcodedPositionAndSize() {
		assertEquals("position", ApplicationFrame.INITIAL_POSITION, frame.getLocation());
		assertEquals("size", ApplicationFrame.INITIAL_SIZE, frame.getSize());
	}
	
	@Test
	public void shouldContainAppropriateComponents() {
		Component[] components = frame.getContentPane().getComponents();
		assertEquals("# of components", 1, components.length);
	}
	
}
