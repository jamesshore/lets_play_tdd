package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import org.junit.*;
import com.jamesshore.spikes.looktest.*;


public class _AlternatingRowTableTest {
	
	private JFrame frame;

	@Before
	public void setup() {
		frame = new JFrame();
	}
	
	@After
	public void teardown() {
		frame.setVisible(false);
	}

	private JTable getNewTable(TableModel tableModel) {
		frame.getContentPane().add(new AlternatingRowTable(tableModel));
		frame.setVisible(true);
		frame.pack();
		JTable table = (AlternatingRowTable)frame.getContentPane().getComponent(0);
		return table;
	}

	private DefaultTableModel tableModel() {
	}

	@Test
	public void tableWithJustOneCell_CellUsesDefaultBackgroundColor() {
		DefaultTableModel tableModel = new DefaultTableModel(0, 3);
		tableModel.addRow(new String[] {"a", "b", "c"});
		tableModel.addRow(new String[] {"1", "2", "3"});
		tableModel.addRow(new String[] {"I", "II", "III"});
		
		Object[][] testData = new Object[][] { new Object[] { "" } };
		Object[] noColumnNames = null;
		TableModel model = new DefaultTableModel(testData, noColumnNames);

		JTable table = getNewTable(model);
		
		TableCellRenderer renderer = table.getCellRenderer(0, 0);
		Component component = table.prepareRenderer(renderer, 0, 0);
		Color actualColor = component.getBackground();
		
		assertEquals(Color.WHITE, actualColor);
	}
	
	//@Test
	//public void secondRowShouldHaveAlternateBackgroundColor() {
	
	
	
}
