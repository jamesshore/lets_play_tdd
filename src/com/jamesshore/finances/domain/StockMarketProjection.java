package com.jamesshore.finances.domain;

import com.jamesshore.finances.util.*;
import com.jamesshore.finances.values.*;

public class StockMarketProjection {

	private final Year startingYear;
	private final Year endingYear;
	private StockMarketYear[] years;
	private final Dollars sellEveryYear;

	public StockMarketProjection(StockMarketYear firstYear, Year endingYear, Dollars sellEveryYear) {
		this.startingYear = firstYear.year();
		this.endingYear = endingYear;
		this.sellEveryYear = sellEveryYear;
		populateYears(firstYear);
	}

	private void populateYears(StockMarketYear firstYear) {
		this.years = new StockMarketYear[numberOfYears()];
		years[0] = firstYear;
		years[0].sell(sellEveryYear);
		for (int i = 1; i < numberOfYears(); i++) {
			years[i] = years[i - 1].nextYear();
			years[i].sell(sellEveryYear);
		}
	}
	
	public int numberOfYears() {
		return startingYear.numberOfYearsInclusive(endingYear);
	}
	
	public StockMarketYear getYearOffset(int offset) {
		Require.that(offset >= 0 && offset < numberOfYears(), "Offset needs to be between 0 and " + (numberOfYears() - 1) + "; was " + offset);
		return years[offset];
	}

}
