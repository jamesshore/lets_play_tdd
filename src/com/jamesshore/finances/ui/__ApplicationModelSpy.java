package com.jamesshore.finances.ui;

import com.jamesshore.finances.domain.*;

public class __ApplicationModelSpy extends ApplicationModel {
	public Dollars setStartingBalanceCalledWith;
	public Dollars setStartingCostBasisCalledWith;
	public Dollars setYearlySpendingCalledWith;

	@Override
	public void setStartingBalance(Dollars startingBalance) {
		setStartingBalanceCalledWith = startingBalance;
	}

	@Override
	public void setStartingCostBasis(Dollars startingCostBasis) {
		setStartingCostBasisCalledWith = startingCostBasis;
	}

	@Override
	public void setYearlySpending(Dollars yearlySpending) {
		setYearlySpendingCalledWith = yearlySpending;
	}
}