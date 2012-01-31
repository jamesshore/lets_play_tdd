package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import javax.swing.event.*;
import org.junit.*;
import com.jamesshore.finances.domain.*;

public class _StockMarketTableModelTest {

	private static final Year STARTING_YEAR = new Year(2010);
	private static final Year ENDING_YEAR = new Year(2050);
	private static final Dollars STARTING_BALANCE = new ValidDollars(10000);
	private static final Dollars STARTING_COST_BASIS = new ValidDollars(7000);
	private static final Dollars YEARLY_SPENDING = new ValidDollars(36);

	private StockMarketYear startingYear;
	private StockMarketTableModel model;

	@Before
	public void setup() {
		startingYear = new StockMarketYear(STARTING_YEAR, STARTING_BALANCE, STARTING_COST_BASIS, new GrowthRate(10), new TaxRate(25));
		StockMarketProjection projection = new StockMarketProjection(startingYear, ENDING_YEAR, YEARLY_SPENDING);
		model = new StockMarketTableModel(projection);
	}

	@Test
	public void startingValues() {
		assertEquals(STARTING_BALANCE, model.startingBalance());
		assertEquals(STARTING_COST_BASIS, model.startingCostBasis());
		assertEquals(YEARLY_SPENDING, model.yearlySpending());
	}

	@Test
	public void columnValues() {
		assertEquals(7, model.getColumnCount());
		assertEquals("year", STARTING_YEAR, model.getValueAt(0, 0));
		assertEquals("starting balance", STARTING_BALANCE, model.getValueAt(0, 1));
		assertEquals("starting principal", STARTING_COST_BASIS, model.getValueAt(0, 2));
		assertEquals("sell orders", new ValidDollars(0).minus(YEARLY_SPENDING), model.getValueAt(0, 3));
		assertEquals("taxes", new ValidDollars(-12), model.getValueAt(0, 4));
		assertEquals("appreciation", new ValidDollars(995), model.getValueAt(0, 5));
		assertEquals("ending balance", new ValidDollars(10947), model.getValueAt(0, 6));
	}

	@Test
	public void columnNames() {
		assertEquals("# of column names should match number of columns", model.getColumnCount(), StockMarketTableModel.COLUMN_TITLES.length);
		assertEquals("should map column names to correct column", StockMarketTableModel.COLUMN_TITLES[2], model.getColumnName(2));
	}

	@Test
	public void columnClasses() {
		assertEquals("# of column classes should match number of columns", model.getColumnCount(), StockMarketTableModel.COLUMN_CLASSES.length);
		for (int i = 0; i < model.getColumnCount(); i++) {
			Class<?> actual = model.getValueAt(0, i).getClass();
			Class<?> declared = model.getColumnClass(i);
			String message = String.format("declared class for column %d (%s) is not compatible actual class (%s)", i, declared, actual);
			assertTrue(message, declared.isAssignableFrom(actual));
			assertFalse("declared class for column " + i + " can not be Object", declared.equals(Object.class));
		}
	}

	@Test
	public void multipleRows() {
		assertEquals(41, model.getRowCount());
		assertEquals(STARTING_YEAR, model.getValueAt(0, 0));
		assertEquals(STARTING_BALANCE, model.getValueAt(0, 1));
		assertEquals(new ValidDollars(10947), model.getValueAt(1, 1));
		assertEquals(ENDING_YEAR, model.getValueAt(40, 0));
	}

	@Test
	public void setProjection_ShouldChangeTableModel() {
		StockMarketProjection projection = new StockMarketProjection(startingYear, startingYear.year(), new ValidDollars(0));
		model.setProjection(projection);
		assertEquals("projection should have changed", projection, model.stockMarketProjection());
		assertEquals("change to projection should reflect in methods", 1, model.getRowCount());
	}

	@Test
	public void setProjection_ShouldFireUpdateEvent() {
		StockMarketProjection projection = new StockMarketProjection(startingYear, startingYear.year(), new ValidDollars(0));

		class TestListener implements TableModelListener {
			public boolean eventFired = false;
			public Integer firstRowChanged = null;
			public Integer lastRowChanged = null;

			public void tableChanged(TableModelEvent e) {
				eventFired = true;
				firstRowChanged = e.getFirstRow();
				lastRowChanged = e.getLastRow();
			}
		}
		TestListener listener = new TestListener();
		model.addTableModelListener(listener);

		model.setProjection(projection);
		assertTrue("event should have been fired", listener.eventFired);
		assertEquals("whole table should change (first row)", 0, listener.firstRowChanged.intValue());
		assertEquals("whole table should change (last row)", Integer.MAX_VALUE, listener.lastRowChanged.intValue());
	}

}
