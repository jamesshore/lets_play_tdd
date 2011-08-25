package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import org.junit.*;

public class _ApplicationFrameTest {

	private ApplicationFrame frame;
	private ApplicationModel model;

	@Before
	public void setup() {
		model = new ApplicationModel();
		frame = new ApplicationFrame(model);
	}

	@Test
	public void shouldExitApplicationWhenWindowClosed() {
		assertEquals("should exit on close", WindowConstants.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
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
	public void shouldHaveMenu() {
		JMenuBar menuBar = frame.getJMenuBar();

		assertNotNull("should have menu bar", menuBar);
		assertEquals("# of menus", 1, menuBar.getMenuCount());
		assertEquals("file menu title", "File", menuBar.getMenu(0).getText());
	}

	@Test
	public void shouldLayoutProperly() {
		assertEquals("layout", BorderLayout.class, frame.getContentPane().getLayout().getClass());

		Component[] components = frame.getContentPane().getComponents();
		assertEquals("# of components", 2, components.length);
		assertEquals("scroll pane", JScrollPane.class, components[0].getClass());
		assertEquals("scroll pane should contain table", ForecastTable.class, ((JScrollPane)components[0]).getViewport().getView().getClass());
		assertEquals("configuration panel", ConfigurationPanel.class, components[1].getClass());
	}

	@Test
	public void forecastTableShouldContainCorrectModel() {
		JScrollPane scrollPane = (JScrollPane)frame.getContentPane().getComponent(0);
		TableModel model = ((ForecastTable)scrollPane.getViewport().getView()).getModel();
		assertEquals("forecast table model class", StockMarketTableModel.class, model.getClass());
		assertEquals("# of rows in model", 41, model.getRowCount());
	}

}
