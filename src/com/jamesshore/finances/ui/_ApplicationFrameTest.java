package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
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
		assertEquals("component #0 class", JScrollPane.class, components[0].getClass());
	}
	
	@Test
	public void forecastTableShouldContainCorrectModel() {
		TableModel model = ((ForecastTable)frame.getContentPane().getComponent(0)).getModel();
		assertEquals("forecast table model class", StockMarketTableModel.class, model.getClass());
		assertEquals("# of rows in model", 41, model.getRowCount());
	}
	
}
