package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import org.junit.*;
import com.jamesshore.spikes.looktest.*;


public class _AlternatingRowTableTest {
	
	@Test
	public void tableRowShouldUseStandardColor_WhenJustOneRow() {
		DefaultTableModel tableModel = new DefaultTableModel(0, 1);
		tableModel.addRow(new String[] {""});
		JTable table = new AlternatingRowTable(tableModel);

		assertEquals("row 0 should have standard background", AlternatingRowTable.STANDARD_BACKGROUND_COLOR, getCellBackground(table, 0, 0));
	}

	@Test
	public void tableRowsShouldAlternateColors_WhenThereAreNoColumnHeaders() {
		DefaultTableModel tableModel = new DefaultTableModel(0, 1);
		tableModel.addRow(new String[] {""});
		tableModel.addRow(new String[] {""});
		tableModel.addRow(new String[] {""});
		tableModel.addRow(new String[] {""});
		JTable table = new AlternatingRowTable(tableModel);

		assertEquals("row 0 should have standard background", AlternatingRowTable.STANDARD_BACKGROUND_COLOR, getCellBackground(table, 0, 0));
		assertEquals("row 1 should have alternate background", AlternatingRowTable.ALTERNATE_BACKGROUND_COLOR, getCellBackground(table, 1, 0));
		assertEquals("row 2 should have standard background", AlternatingRowTable.STANDARD_BACKGROUND_COLOR, getCellBackground(table, 2, 0));
		assertEquals("row 3 should have alternate background", AlternatingRowTable.ALTERNATE_BACKGROUND_COLOR, getCellBackground(table, 3, 0));
	}

	// TODO: tableRowsShouldAlternateColors_WhenThereAreColumnHeaders
	
	private Color getCellBackground(JTable table, int row, int column) {
		TableCellRenderer renderer = table.getCellRenderer(row, column);
		Component component = table.prepareRenderer(renderer, row, column);
		Color actualColor = component.getBackground();
		return actualColor;
	}
	
}
