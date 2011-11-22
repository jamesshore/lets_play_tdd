// This file commented out so everything would compile without SWT being needed in the build path.
// Having SWT in the build path prevents Swing from loading properly, which messes up the tests.

//package com.jamesshore.spikes.swt;
//
//import org.eclipse.swt.*;
//import org.eclipse.swt.events.*;
//import org.eclipse.swt.layout.*;
//import org.eclipse.swt.widgets.*;
//
//public class SwtSpike {
//
//	public static void main (String[] args) {
//		Display display = new Display();
//		Shell shell = new Shell(display);
//		layout(shell);
//		shell.pack();
//		shell.open();
//		while (!shell.isDisposed()) {
//			if (!display.readAndDispatch()) display.sleep();
//		}
//		display.dispose();
//	}
//
//	private static void layout(final Shell shell) {
//		shell.setLayout(new RowLayout(SWT.VERTICAL));
//
//		createMenu(shell);
//		Table table = createTable(shell);
//		createButton(shell, table);
//	}
//
//	private static void createMenu(final Shell shell) {
//		Menu bar = new Menu(shell, SWT.BAR);
//		shell.setMenuBar(bar);
//		MenuItem fileItem = new MenuItem(bar, SWT.CASCADE);
//		fileItem.setText("&File");
//		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
//		fileItem.setMenu(fileMenu);
//		MenuItem close = new MenuItem(fileMenu, SWT.PUSH);
//		close.setText("Close");
//		close.setAccelerator(SWT.MOD1 + 'W');
//		close.addListener(SWT.Selection, new Listener() {
//			public void handleEvent(Event e) {
//				shell.close();
//			}
//		});
//	}
//
//	private static Table createTable(final Shell shell) {
//		Table table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.SCROLL_LINE);
//		table.setLinesVisible(true);
//		table.setHeaderVisible(true);
//		
//		RowData data = new RowData(SWT.DEFAULT, 500);
//		table.setLayoutData(data);
//		
//		String[] titles = {"Year", "Starting Balance", "Starting Principal", "Withdrawals", "Appreciation", "Deposits", "Ending Balance"};
//		for (String title : titles) {
//			TableColumn column = new TableColumn(table, SWT.NONE);
//			column.setText(title);
//		}
//		
//		for (int i = 0; i < 12800; i++) {
//			TableItem item = new TableItem(table, SWT.NONE);
//			item.setText(0, "" + (1900 + i));
//			item.setText(1, "" + (10000 + i));
//			item.setText(2, "" + (8000 + i));
//			item.setText(3, "" + (50 + i));
//			item.setText(4, "" + (905 + i));
//			item.setText(5, "" + (2000 + i));
//			item.setText(6, "" + (12000 + i));
//		}
//		for (int i = 0; i < titles.length; i++) {
//			table.getColumn(i).pack();
//		}
//		
//		return table;
//	}
//	
//	private static void createButton(final Shell shell, final Table table) {
//		final Button button = new Button(shell, SWT.PUSH);
//		button.setText("I'm a button!");
//		button.addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent e) {
//				button.setText("The world will end in " + (Math.random() * 100000) + " seconds");
//				for (TableItem row : table.getItems()) {
//					int currentValue = Integer.parseInt(row.getText(1));
//					row.setText(1, "" + (int)(currentValue * 1.03));
//				}
//				for (int i = 0; i < 7; i++) {
//					table.getColumn(i).pack();
//				}
//				shell.pack();
//			}
//		});
//	}
//	
// }
