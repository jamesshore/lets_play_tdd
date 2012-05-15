package com.jamesshore.finances.persistence;

import java.io.*;
import com.jamesshore.finances.values.*;

public class UserConfiguration {

	public static boolean STUB_OUT_FILE_SYSTEM_FOR_TESTING_ONLY = false;

	public static final UserEnteredDollars DEFAULT_STARTING_BALANCE = new UserEnteredDollars("10000");
	public static final UserEnteredDollars DEFAULT_STARTING_COST_BASIS = new UserEnteredDollars("7000");
	public static final UserEnteredDollars DEFAULT_YEARLY_SPENDING = new UserEnteredDollars("695");

	private File path = null;

	public UserEnteredDollars startingBalance = DEFAULT_STARTING_BALANCE;
	public UserEnteredDollars startingCostBasis = DEFAULT_STARTING_COST_BASIS;
	public UserEnteredDollars yearlySpending = DEFAULT_YEARLY_SPENDING;

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
			writeLine(writer, startingBalance.getUserText());
			writeLine(writer, startingCostBasis.getUserText());
			writeLine(writer, yearlySpending.getUserText());
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
}
