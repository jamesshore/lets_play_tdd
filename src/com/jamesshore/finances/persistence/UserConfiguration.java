package com.jamesshore.finances.persistence;

import java.io.*;
import com.jamesshore.finances.values.*;

public class UserConfiguration {

	public static boolean STUB_OUT_FILE_SYSTEM_FOR_TESTING_ONLY = false;

	private File path;
	private boolean hasSaved = false;

	public UserConfiguration(File path) {
		this.path = path;
	}

	public File path() {
		return path;
	}

	public void save(UserEnteredDollars startingBalance, UserEnteredDollars costBasis, UserEnteredDollars yearlySpending) throws IOException {
		writeFile(startingBalance, costBasis, yearlySpending);
		hasSaved = true;
	}

	private void writeFile(UserEnteredDollars startingBalance, UserEnteredDollars costBasis, UserEnteredDollars yearlySpending) throws IOException {
		if (STUB_OUT_FILE_SYSTEM_FOR_TESTING_ONLY) return;

		Writer writer = new BufferedWriter(new FileWriter(path));
		try {
			writeLine(writer, "com.jamesshore.finances,1");
			writeLine(writer, startingBalance.getUserText());
			writeLine(writer, costBasis.getUserText());
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

	public boolean hasEverBeenSaved() {
		return hasSaved;
	}
}
