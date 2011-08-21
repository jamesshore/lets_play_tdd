package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import javax.swing.event.*;
import org.junit.*;
import com.jamesshore.finances.domain.*;

public class _StockMarketTableModelTest {

	private static final Year STARTING_YEAR = new Year(2010);
	private static final Year ENDING_YEAR = new Year(2050);
	private static final Dollars STARTING_BALANCE = ValidDollars.create(10000);
	private static final Dollars STARTING_COST_BASIS = ValidDollars.create(7000);
	private static final Dollars YEARLY_SPENDING = ValidDollars.create(36);

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
	public void columns() {
		assertEquals(7, model.getColumnCount());
		assertEquals("Year", model.getColumnName(0));
		assertEquals("Starting Balance", model.getColumnName(1));
		assertEquals("Cost Basis", model.getColumnName(2));
	}

	@Test
	public void columnClasses() {
		for (int i = 0; i < model.getColumnCount(); i++) {
			Class<?> actual = model.getValueAt(0, i).getClass();
			Class<?> declared = model.getColumnClass(i);
			String message = String.format("declared class for column %d (%s) is not compatible actual class (%s)", i, declared, actual);
			assertTrue(message, declared.isAssignableFrom(actual));
			assertFalse("declared class for column " + i + " can not be Object", declared.equals(Object.class));
		}
	}

	@Test
	public void oneRow() {
		assertEquals("year", STARTING_YEAR, model.getValueAt(0, 0));
		assertEquals("starting balance", STARTING_BALANCE, model.getValueAt(0, 1));
		assertEquals("starting principal", STARTING_COST_BASIS, model.getValueAt(0, 2));
		assertEquals("sell orders", ValidDollars.create(0).minus(YEARLY_SPENDING), model.getValueAt(0, 3));
		assertEquals("taxes", ValidDollars.create(-12), model.getValueAt(0, 4));
		assertEquals("appreciation", ValidDollars.create(995), model.getValueAt(0, 5));
		assertEquals("ending balance", ValidDollars.create(10947), model.getValueAt(0, 6));
	}

	@Test
	public void multipleRows() {
		assertEquals(41, model.getRowCount());
		assertEquals(STARTING_YEAR, model.getValueAt(0, 0));
		assertEquals(STARTING_BALANCE, model.getValueAt(0, 1));
		assertEquals(ValidDollars.create(10947), model.getValueAt(1, 1));
		assertEquals(ENDING_YEAR, model.getValueAt(40, 0));
	}

	@Test
	public void setProjection_ShouldChangeTableModel() {
		StockMarketProjection projection = new StockMarketProjection(startingYear, startingYear.year(), ValidDollars.create(0));
		model.setProjection(projection);
		assertEquals("projection should have changed", projection, model.stockMarketProjection());
		assertEquals("change to projection should reflect in methods", 1, model.getRowCount());
	}

	@Test
	public void setProjection_ShouldFireUpdateEvent() {
		StockMarketProjection projection = new StockMarketProjection(startingYear, startingYear.year(), ValidDollars.create(0));

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
