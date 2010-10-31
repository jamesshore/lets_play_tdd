package com.jamesshore.spikes.looktest;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class CustomTable extends JTable {
	public static final Color PALE_BLUE = new Color(223, 230, 236);
	private static final long serialVersionUID = 1L;
	
	public CustomTable() {
		super();
	}

	public CustomTable(DefaultTableModel tableModel) {
		super(tableModel);
	}

	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		// This code based on http://www.roseindia.net/java/example/java/swing/SadingRows.shtml
		Component component = super.prepareRenderer(renderer, row, column);
		if (isCellSelected(row, column)) return component;
		
		Color background = row % 2 == 0 ? PALE_BLUE : Color.white;
		component.setBackground(background);
		return component;
	}

}
