package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;
import com.jamesshore.finances.domain.*;
import com.jamesshore.finances.persistence.*;
import com.jamesshore.finances.values.*;

public class _ApplicationModelTest {

	private UserConfiguration configuration;
	private ApplicationModel model;

	@Before
	public void setup() {
		UserConfiguration.STUB_OUT_FILE_SYSTEM_FOR_TESTING_ONLY = true;
		model = new ApplicationModel();
		configuration = model.userConfiguration();
	}

	@After
	public void teardown() {
		UserConfiguration.STUB_OUT_FILE_SYSTEM_FOR_TESTING_ONLY = false;
	}

	@Test
	public void shouldStartWithDefaultStockMarket() {
		StockMarketProjection projection = model.stockMarketTableModel().stockMarketProjection();

		StockMarketYear startingYear = projection.getYearOffset(0);
		assertEquals(UserConfiguration.DEFAULT_STARTING_BALANCE, startingYear.startingBalance());
		assertEquals(UserConfiguration.DEFAULT_STARTING_COST_BASIS, startingYear.startingCostBasis());
		assertEquals(UserConfiguration.DEFAULT_YEARLY_SPENDING, startingYear.totalSellOrders());

		assertEquals(ApplicationModel.DEFAULT_STARTING_YEAR, startingYear.year());
		assertEquals(ApplicationModel.DEFAULT_GROWTH_RATE, startingYear.growthRate());
		assertEquals(ApplicationModel.DEFAULT_CAPITAL_GAINS_TAX_RATE, startingYear.capitalGainsTaxRate());

		assertEquals(41, projection.numberOfYears());
	}

	@Test
	public void shouldOnlyHaveOneInstanceOfStockMarketTableModel() {
		assertTrue("should be same instance", model.stockMarketTableModel() == model.stockMarketTableModel());
	}

	@Test
	public void configurationUpdated_ResultsInStockMarketTableModelChanging() {
		configuration.yearlySpending = new UserEnteredDollars("423");
		model.configurationUpdated();
		assertEquals("stock market table model", new ValidDollars(423), model.stockMarketTableModel().yearlySpending());
	}

	@Test
	public void saveShouldSaveConfiguration() throws IOException {
		assertNull("configuration should not be saved", configuration.lastSavedPathOrNullIfNeverSaved());
		File expectedFile = new File("FOO");
		model.save(expectedFile);
		assertEquals("configuration save file", expectedFile, configuration.lastSavedPathOrNullIfNeverSaved());
	}

}
