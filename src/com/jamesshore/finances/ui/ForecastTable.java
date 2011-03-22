package com.jamesshore.finances.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import com.jamesshore.finances.domain.*;

//If you want to subclass this class, it's okay to remove the 'final designator, but be careful of race 
//conditions with the cell renderer in the constructor. It could execute before the subclass constructor.
public final class ForecastTable extends JTable {
	private static final long serialVersionUID = 1L;

	public static final Color STANDARD_BACKGROUND_COLOR = Color.WHITE;
	public static final Color ALTERNATE_BACKGROUND_COLOR = new Color(209, 229, 255);
	public static final Color SELECTION_BACKGROUND_COLOR = new Color(52, 117, 237);

	public ForecastTable(TableModel model) {
		super(model);
		setDefaultRenderer(SelfRenderable.class, selfRenderer());
	}

	private TableCellRenderer selfRenderer() {
		return new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			public void setValue(Object value) {
				SelfRenderable renderable = (SelfRenderable)value;
				renderable.render(this);
			}
		};
	}

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component cell = super.prepareRenderer(renderer, row, column);

		if (isCellSelected(row, column)) cell.setBackground(SELECTION_BACKGROUND_COLOR);
		else if (alternatingRow(row)) cell.setBackground(ALTERNATE_BACKGROUND_COLOR);
		else cell.setBackground(STANDARD_BACKGROUND_COLOR);

		return cell;
	}

	private boolean alternatingRow(int row) {
		return row % 2 == 1;
	}
}
