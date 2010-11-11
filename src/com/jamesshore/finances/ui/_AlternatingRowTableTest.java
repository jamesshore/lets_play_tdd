package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.awt.*;
import javax.swing.table.*;
import org.junit.*;


public class _AlternatingRowTableTest {

	@Test
	public void tableWithJustOneCell_CellUsesDefaultBackgroundColor() {
		Object[][] testData = new Object[][] { new Object[] { "" } };
		Object[] noColumnNames = null;
		TableModel model = new DefaultTableModel(testData, noColumnNames);
		
		AlternatingRowTable table = new AlternatingRowTable(model);
		
		TableCellRenderer renderer = table.getCellRenderer(0, 0);
		Component component = table.prepareRenderer(renderer, 0, 0);
		Color actualColor = component.getBackground();
		
		assertEquals(AlternatingRowTable.DEFAULT_BACKGROUND_COLOR, actualColor);
	}
	
	//@Test
	//public void secondRowShouldHaveAlternateBackgroundColor() {
	
	
	
}
