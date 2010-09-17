package com.jamesshore.spikes.swing;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.TableColumn;
import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;

public class SwingSpike extends JFrame {
	private static final long serialVersionUID = 1L;

	private DefaultTableModel tableModel;
	
	public SwingSpike() {
		setTitle("Swing Spike");
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setJMenuBar(menuBar());
		addComponents();
		pack();
	}
	
	private JMenuBar menuBar() {
		JMenuBar result = new JMenuBar();
		result.add(fileMenu());
		
		return result;
	}
	
	private JMenu fileMenu() {
		JMenu result = new JMenu("File");
		result.add(closeAction());
		return result;
	}

	private JMenuItem closeAction() {
		JMenuItem result = new JMenuItem("Close");
		result.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.META_MASK));
		result.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingSpike.this.dispose();
			}
		});
		return result;
	}
	
	private void addComponents() {
		Container content = this.getContentPane();
		content.add("South", button());
		content.add("Center", table());
	}

	@SuppressWarnings("unchecked")
	private Component button() {
		final JButton button = new JButton("Foo!");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setText("The world will end in " + (Math.random() * 100000) + " seconds");
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					int cell = (Integer)tableModel.getValueAt(i, 1);
					tableModel.setValueAt((int)(cell * 1.03), i, 1);
				}
			}
		});
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.add(button);
		return panel;
	}
	
	private Component table() {
		String[] titles = {"Year", "Starting Balance", "Starting Principal", "Withdrawals", "Appreciation", "Deposits", "Ending Balance"};
		tableModel = new DefaultTableModel(titles, 0);
		for (int i = 0; i < 12800; i++) {
			tableModel.addRow(new Integer[] {1900 + i, 10000 + i, 8000 + i, 50 + i, 905 + i, 2000 + i, 12000 + i});
		}
		
		// This code based on http://www.roseindia.net/java/example/java/swing/SadingRows.shtml
		JTable table = new JTable(tableModel) {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer, row, column);
				if (isCellSelected(row, column)) return component;
				
				Color background = row % 2 == 0 ? new Color(223, 230, 236) : Color.white;
				component.setBackground(background);
				return component;
			}
		};		
		
		int preferredWidth = 0;
		for (int i = 0; i < table.getColumnCount(); i++) {
			int width = packColumn(table, i, 2);
			preferredWidth += width;
		}
		Dimension preferredSize = new Dimension(preferredWidth, 400);
		table.setPreferredScrollableViewportSize(preferredSize);
		this.setMinimumSize(preferredSize);
		
		JScrollPane scrollPane = new JScrollPane(table);
		return scrollPane;
	}

	// This method from http://www.exampledepot.com/egs/javax.swing.table/PackCol.html
	public int packColumn(JTable table, int vColIndex, int margin) {
		DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
		TableColumn col = colModel.getColumn(vColIndex);
		int width = 0; 
		
		// Get width of column header
		TableCellRenderer renderer = col.getHeaderRenderer();
		if (renderer == null) {
			renderer = table.getTableHeader().getDefaultRenderer();
		}
		Component comp = renderer.getTableCellRendererComponent(table, col.getHeaderValue(), false, false, 0, 0);
		width = comp.getPreferredSize().width; 
		
		// Get maximum width of column data
		for (int r = 0; r < table.getRowCount(); r++) {
			renderer = table.getCellRenderer(r, vColIndex);
			comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, vColIndex), false, false, r, vColIndex);
			width = Math.max(width, comp.getPreferredSize().width);
		} 
		
		// Add margin
		width += 2 * margin; 
		
		// Set the width
		col.setPreferredWidth(width);
		
		return width;
	}
	
	public static void main(String[] args) {
   		new SwingSpike().setVisible(true);
	}
	
}
