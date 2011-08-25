package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import org.junit.*;

public class _ApplicationFrameTest {

	private ApplicationFrame frame;
	private ApplicationModel model;

	@Before
	public void setup() throws Exception {
		model = new ApplicationModel();
		frame = new ApplicationFrame(model);
	}

	@After
	public void teardown() {
		// POTENTIAL GOTCHA:
		//
		// I could 'frame.dispose()' here, which would release the resources consumed
		// by creating the frame. However, that could cause tests to fail if they are
		// assuming a certain number of frames, because the number of frames decrements
		// according to the whims of the garbage collector.
		//
		// Also, dispose() is very slow, so I've decided not to dispose of frames at this
		// time. As a result, many frames are being created and then hanging around in
		// memory consuming graphic resources. This could cause out-of-memory errors or
		// some other problems in the future, so good luck to future me.
	}

	@Test
	public void shouldExitApplicationWhenWindowClosed() throws Exception {
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

		JMenu fileMenu = menuBar.getMenu(0);
		assertEquals("file menu title", "File", fileMenu.getText());
		assertEquals("# of menu items", 1, fileMenu.getItemCount());
		JMenuItem newMenuItem = fileMenu.getItem(0);
		assertEquals("'new' menu item", "New", newMenuItem.getText());

		KeyStroke newMenuItemAccelerator = newMenuItem.getAccelerator();
		assertNotNull("'new' menu item should have accelerator", newMenuItemAccelerator);
		assertEquals("'new' accelerator key", KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.META_MASK), newMenuItemAccelerator);
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

	@Test
	public void newWindow() {
		int frameCount = Frame.getFrames().length;
		ApplicationFrame.newWindow();

		Frame[] allFrames = Frame.getFrames();
		assertEquals("number of windows should increase by 1", frameCount + 1, allFrames.length);
		assertTrue("new window should be visible", allFrames[allFrames.length - 1].isVisible());
	}

	@Test
	public void newMenuItemShouldCreateANewWindow() throws Throwable {
		int frameCount = Frame.getFrames().length;

		// TODO: Clean up duplication with shouldHaveMenu()
		JMenuBar menuBar = frame.getJMenuBar();
		JMenu fileMenu = menuBar.getMenu(0);
		JMenuItem newMenuItem = fileMenu.getItem(0);
		newMenuItem.doClick();

		Frame[] allFrames = Frame.getFrames();
		assertEquals("number of windows should increase by 1", frameCount + 1, allFrames.length);
		assertTrue("new window should be visible", allFrames[allFrames.length - 1].isVisible());
	}

}
