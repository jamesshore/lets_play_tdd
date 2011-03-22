package com.jamesshore.spikes.icon_in_table;

import java.awt.*;
import java.net.*;
import javax.swing.*;
import javax.swing.table.*;

public class IconInTableSpike extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final Point INITIAL_POSITION = new Point(400, 300);
	public static final Dimension INITIAL_SIZE = new Dimension(900, 400);

	private DefaultTableModel tableModel;

	public IconInTableSpike() {
		setTitle("Icon In Table");
		setSize(INITIAL_SIZE);
		setLocation(INITIAL_POSITION);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addComponents();
	}

	private void addComponents() {
		Container content = this.getContentPane();
		content.add("Center", table());
	}

	@SuppressWarnings("serial")
	private Component table() {
		String[] titles = { "Year", "Starting Balance", "Starting Principal", "Withdrawals", "Appreciation", "Deposits", "Ending Balance" };
		tableModel = new DefaultTableModel(titles, 0) {
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 1) return SpikeDollars.class;
				else return super.getColumnClass(columnIndex);
			}
		};
		for (int i = 0; i < 12800; i++) {
			tableModel.addRow(new Integer[] { 1900 + i, -1, 8000 + i, 50 + i, 905 + i, 2000 + i, 12000 + i });
			tableModel.setValueAt(new SpikeDollars(10000 + i), i, 1);
		}

		JTable table = newTable();
		// TableColumn column = table.getColumnModel().getColumn(1);
		// column.setCellRenderer(renderer());
		table.setDefaultRenderer(SpikeDollars.class, renderer());

		JScrollPane scrollPane = new JScrollPane(table);
		return scrollPane;
	}

	@SuppressWarnings("serial")
	private JTable newTable() {
		return new JTable(tableModel) {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer, row, column);
				if (isCellSelected(row, column)) return component;

				Color background = row % 2 == 0 ? new Color(223, 230, 236) : Color.white;
				component.setBackground(background);
				return component;
			}
		};
	}

	@SuppressWarnings("serial")
	private TableCellRenderer renderer() {
		return new DefaultTableCellRenderer() {
			public void setValue(Object value) {
				SpikeDollars dollars = (SpikeDollars)value;

				setForeground(Color.BLACK);
				setText(null);
				setIcon(null);
				setToolTipText(dollars.toString());

				if (dollars.amount < 10005) {
					setText("<");
					this.setForeground(Color.RED);
				}
				else if (dollars.amount > 10005) {
					setText(">");
				}
				else {
					URL iconUrl = getClass().getResource("star.gif");
					setIcon(new ImageIcon(iconUrl));
					setToolTipText("WINNER!");
				}
			}
		};
	}

	public static void main(String[] args) {
		new IconInTableSpike().setVisible(true);
	}

}
