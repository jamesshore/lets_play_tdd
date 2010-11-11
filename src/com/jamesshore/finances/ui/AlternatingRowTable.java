package com.jamesshore.finances.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class AlternatingRowTable extends JTable {

	public static final Color DEFAULT_BACKGROUND_COLOR = Color.GREEN;

	public AlternatingRowTable(TableModel model) {
		super(model);
	}

}
