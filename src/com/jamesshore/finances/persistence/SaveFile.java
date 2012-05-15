package com.jamesshore.finances.persistence;

import java.io.*;
import com.jamesshore.finances.values.*;

public class SaveFile {

	private File path;

	public SaveFile(File path) {
		this.path = path;
	}

	public File path() {
		return path;
	}

	public void save(UserEnteredDollars startingBalance, UserEnteredDollars costBasis, UserEnteredDollars yearlySpending) throws IOException {
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

	public void writeLine(Writer writer, String line) throws IOException {
		line = line.replace("\\", "\\\\");
		line = line.replace("\n", "\\n");
		writer.write(line + "\n");
	}

	public boolean hasSaved() {
		return false;
	}
}
