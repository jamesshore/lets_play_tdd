package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import org.junit.*;
import com.jamesshore.spikes.looktest.*;


public class _AlternatingRowTableTest {

	@Test
	public void tableWithJustOneCell_CellUsesStandardBackgroundColor() {
		DefaultTableModel tableModel = new DefaultTableModel(0, 1);
		tableModel.addRow(new String[] {""});
		
		JTable table = getNewTable(tableModel);
		Color actualColor = getCellBackground(table, 0, 0);
		
		assertEquals(AlternatingRowTable.STANDARD_BACKGROUND_COLOR, actualColor);
	}
	
	@Test
	public void tableWithTwoRows_CellOnSecondRowUsesAltBackgroundColor() {
		DefaultTableModel tableModel = new DefaultTableModel(0, 1);
		tableModel.addRow(new String[] {""});
		tableModel.addRow(new String[] {""});
		
		JTable table = getNewTable(tableModel);
		Color actualColor = getCellBackground(table, 1, 0);
		
		assertEquals(AlternatingRowTable.ALTERNATE_BACKGROUND_COLOR, actualColor);
	}
	
	@Test
	public void tableWithFourRows_CellOnThirdRowUsesAltBackgroundColor() {
		DefaultTableModel tableModel = new DefaultTableModel(0, 1);
		tableModel.addRow(new String[] {""});
		tableModel.addRow(new String[] {""});
		tableModel.addRow(new String[] {""});
		tableModel.addRow(new String[] {""});
		
		JTable table = getNewTable(tableModel);
		Color actualColor = getCellBackground(table, 2, 0);
		
		assertEquals(AlternatingRowTable.STANDARD_BACKGROUND_COLOR, actualColor);
	}

	@Test
	public void tableWithFourRows_CellOnFourthRowUsesAltBackgroundColor() {
		DefaultTableModel tableModel = new DefaultTableModel(0, 1);
		tableModel.addRow(new String[] {""});
		tableModel.addRow(new String[] {""});
		tableModel.addRow(new String[] {""});
		tableModel.addRow(new String[] {""});
		
		JTable table = getNewTable(tableModel);

		
//		JFrame frame = new JFrame();
//		frame.getContentPane().add(new CustomTable(tableModel));
//		frame.setVisible(true);
//		frame.pack();

		
		
		Color actualColor = getCellBackground(table, 3, 0);
		assertEquals(AlternatingRowTable.ALTERNATE_BACKGROUND_COLOR, actualColor);
		
		actualColor = getCellBackground(table, 2, 0);
		assertEquals(AlternatingRowTable.STANDARD_BACKGROUND_COLOR, actualColor);
		
	}

	private Color getCellBackground(JTable table, int row, int column) {
		TableCellRenderer renderer = table.getCellRenderer(row, column);
		Component component = table.prepareRenderer(renderer, row, column);
		Color actualColor = component.getBackground();
		return actualColor;
	}
	
	private JTable getNewTable(TableModel tableModel) {
		return new AlternatingRowTable(tableModel);
	}
	
}
