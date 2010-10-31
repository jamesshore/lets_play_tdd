package com.jamesshore.spikes.looktest;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class CustomTable extends JTable {
	private static final long serialVersionUID = 1L;

	public CustomTable(DefaultTableModel tableModel) {
		super(tableModel);
	}
	
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component component = super.prepareRenderer(renderer, row, column);
		if (isCellSelected(row, column)) return component;
		
		Color background = row % 2 == 0 ? new Color(223, 230, 236) : Color.white;
		component.setBackground(background);
		return component;
	}

}
