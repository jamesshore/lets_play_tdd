package com.jamesshore.spikes.looktest;

import static org.junit.Assert.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import org.junit.*;


public class _LookSpikeTest {

	private JFrame frame;
	private CustomTable table;
	
	@Before
	public void setup() {
		frame = new JFrame();
		DefaultTableModel tableModel = new DefaultTableModel(0, 3);
		tableModel.addRow(new String[] {"a", "b", "c"});
		tableModel.addRow(new String[] {"1", "2", "3"});
		tableModel.addRow(new String[] {"I", "II", "III"});
		frame.getContentPane().add(new CustomTable(tableModel));
		frame.setVisible(true);
		frame.pack();
		table = (CustomTable)frame.getContentPane().getComponent(0);
	}

	@After
	public void teardown() {
		frame.setVisible(false);
	}
	
	@Test
	@Ignore
	public void nothing() throws Exception {
		assertEquals(CustomTable.PALE_BLUE, backgroundOfCell(0, 0));
		assertEquals(CustomTable.PALE_BLUE, backgroundOfCell(0, 1));
		assertEquals(CustomTable.PALE_BLUE, backgroundOfCell(0, 2));
		assertEquals(Color.WHITE, backgroundOfCell(1, 0));
		assertEquals(Color.WHITE, backgroundOfCell(1, 1));
		assertEquals(Color.WHITE, backgroundOfCell(1, 2));
		assertEquals(CustomTable.PALE_BLUE, backgroundOfCell(2, 0));
		assertEquals(CustomTable.PALE_BLUE, backgroundOfCell(2, 1));
		assertEquals(CustomTable.PALE_BLUE, backgroundOfCell(2, 2));
	}

	private Color backgroundOfCell(int row, int column) {
		TableCellRenderer renderer = table.getCellRenderer(row, column);
		Component component = table.prepareRenderer(renderer, row, column);
		Color actualColor = component.getBackground();
		return actualColor;
	}
}
