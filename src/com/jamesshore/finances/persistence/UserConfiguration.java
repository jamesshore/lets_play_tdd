package com.jamesshore.finances.persistence;

import java.io.*;
import com.jamesshore.finances.util.*;
import com.jamesshore.finances.values.*;

public class UserConfiguration {

	public static boolean STUB_OUT_FILE_SYSTEM_FOR_TESTING_ONLY = false;

	public static final UserEnteredDollars DEFAULT_STARTING_BALANCE = new UserEnteredDollars("10000");
	public static final UserEnteredDollars DEFAULT_STARTING_COST_BASIS = new UserEnteredDollars("7000");
	public static final UserEnteredDollars DEFAULT_YEARLY_SPENDING = new UserEnteredDollars("695");

	private Observer observer = null;
	private UserEnteredDollars startingBalance = DEFAULT_STARTING_BALANCE;
	private UserEnteredDollars startingCostBasis = DEFAULT_STARTING_COST_BASIS;
	private UserEnteredDollars yearlySpending = DEFAULT_YEARLY_SPENDING;
	private File path = null;

	public File lastSavedPathOrNullIfNeverSaved() {
		return path;
	}

	public void save(File path) throws IOException {
		writeFile(path);
		this.path = path;
	}

	private void writeFile(File path) throws IOException {
		if (STUB_OUT_FILE_SYSTEM_FOR_TESTING_ONLY) return;

		Writer writer = new BufferedWriter(new FileWriter(path));
		try {
			writeLine(writer, "com.jamesshore.finances,1");
			writeLine(writer, getStartingBalance().getUserText());
			writeLine(writer, getStartingCostBasis().getUserText());
			writeLine(writer, getYearlySpending().getUserText());
		}
		finally {
			writer.close();
		}
	}

	private void writeLine(Writer writer, String line) throws IOException {
		line = line.replace("\\", "\\\\");
		line = line.replace("\n", "\\n");
		writer.write(line + "\n");
	}

	public void setStartingBalance(UserEnteredDollars startingBalance) {
		this.startingBalance = startingBalance;
		notifyObserver();
	}

	public UserEnteredDollars getStartingBalance() {
		return startingBalance;
	}

	public void setStartingCostBasis(UserEnteredDollars startingCostBasis) {
		this.startingCostBasis = startingCostBasis;
		notifyObserver();
	}

	public UserEnteredDollars getStartingCostBasis() {
		return startingCostBasis;
	}

	public void setYearlySpending(UserEnteredDollars yearlySpending) {
		this.yearlySpending = yearlySpending;
		notifyObserver();
	}

	public UserEnteredDollars getYearlySpending() {
		return yearlySpending;
	}

	public void setObserver(Observer observer) {
		Require.that(this.observer == null, "Tried to set a second observer; only one is supported so far");
		this.observer = observer;
	}

	private void notifyObserver() {
		if (observer != null) observer.configurationUpdated();
	}

	public interface Observer {
		public void configurationUpdated();
	}
}
