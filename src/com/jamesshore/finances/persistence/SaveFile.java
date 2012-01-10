package com.jamesshore.finances.persistence;

import java.io.*;
import com.jamesshore.finances.domain.*;

public class SaveFile {

	private File path;

	public SaveFile(File path) {
		this.path = path;
	}

	public void save(Dollars startingBalance) throws IOException {
		Writer writer = new BufferedWriter(new FileWriter(path));
		try {
			writer.write("com.jamesshore.finances,1\n");
			writer.write(startingBalance + "\n");
		}
		finally {
			writer.close();
		}

	}

}
