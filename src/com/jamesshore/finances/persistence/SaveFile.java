package com.jamesshore.finances.persistence;

import java.io.*;
import com.jamesshore.finances.values.*;

public class SaveFile {

	private File path;

	public SaveFile(File path) {
		this.path = path;
	}

	public void save(UserEnteredDollars startingBalance, UserEnteredDollars costBasis, UserEnteredDollars yearlySpending) throws IOException {
		Writer writer = new BufferedWriter(new FileWriter(path));
		try {
			writer.write("com.jamesshore.finances,1\n");
			writer.write(startingBalance.getUserText() + "\n");
			writer.write(costBasis.getUserText() + "\n");
			writer.write(yearlySpending.getUserText() + "\n");
		}
		finally {
			writer.close();
		}

	}

}
