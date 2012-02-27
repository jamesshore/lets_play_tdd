package com.jamesshore.finances.ui;

import java.io.*;
import com.jamesshore.finances.values.*;

public class __ApplicationModelSpy extends ApplicationModel {
	public Dollars setStartingBalanceCalledWith;
	public Dollars setStartingCostBasisCalledWith;
	public Dollars setYearlySpendingCalledWith;
	public File saveCalledWith;

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

	@Override
	public void save(File saveFile) {
		saveCalledWith = saveFile;
	}
}