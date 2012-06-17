package com.jamesshore.finances.ui;

import java.io.*;
import com.jamesshore.finances.values.*;

public class __ApplicationModelSpy extends ApplicationModel {
	public boolean configurationUpdatedCalled = false;
	public UserEnteredDollars setYearlySpendingCalledWith;
	public File saveCalledWith;

	@Override
	public void setYearlySpending(UserEnteredDollars yearlySpending) {
		setYearlySpendingCalledWith = yearlySpending;
	}

	@Override
	public void save(File saveFile) throws IOException {
		saveCalledWith = saveFile;
	}

	@Override
	public void configurationUpdated() {
		configurationUpdatedCalled = true;
	}
}