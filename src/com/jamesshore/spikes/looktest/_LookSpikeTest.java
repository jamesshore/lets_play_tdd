package com.jamesshore.spikes.looktest;

import static org.junit.Assert.*;
import java.awt.*;
import javax.swing.*;
import org.junit.*;


public class _LookSpikeTest {

	@Test
	public void nothing() throws Exception {
		JFrame frame = new LookSpike();
		try {
			frame.setVisible(true);
			Point point = frame.getLocation();
			assertEquals(new Point(300, 200), point);
		}
		finally {
			frame.setVisible(false);
		}
	}
}
