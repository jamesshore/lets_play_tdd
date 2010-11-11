package com.jamesshore.finances.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class AlternatingRowTable extends JTable {
	private static final long serialVersionUID = 1L;

	public static final Color STANDARD_BACKGROUND_COLOR = Color.GREEN;
	public static final Color ALTERNATE_BACKGROUND_COLOR = Color.RED;

	public AlternatingRowTable(TableModel model) {
		super(model);
	}
	
	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component cell = super.prepareRenderer(renderer, row, column);
		
		if (alternatingRow(row)) cell.setBackground(ALTERNATE_BACKGROUND_COLOR);
		else cell.setBackground(STANDARD_BACKGROUND_COLOR);
		
		return cell;
	}

	private boolean alternatingRow(int row) {
		return row % 2 == 1;
	}
}
