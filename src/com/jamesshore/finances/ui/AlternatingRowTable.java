package com.jamesshore.finances.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class AlternatingRowTable extends JTable {
	private static final long serialVersionUID = 1L;

	public static final Color STANDARD_BACKGROUND_COLOR = Color.WHITE;
	public static final Color ALTERNATE_BACKGROUND_COLOR = Color.PINK;

	public AlternatingRowTable(TableModel model) {
		super(model);
		this.setBackground(STANDARD_BACKGROUND_COLOR);
	}

}
