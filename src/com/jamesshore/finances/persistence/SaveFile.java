package com.jamesshore.finances.persistence;

import java.io.*;

public class SaveFile {

	private File path;

	public SaveFile(File path) {
		this.path = path;
	}

	public void save() throws IOException {
		path.delete();
		path.createNewFile();
	}

}
