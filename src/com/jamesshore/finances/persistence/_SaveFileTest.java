package com.jamesshore.finances.persistence;

import org.junit.*;

public class _SaveFileTest {

	@Test
	public void saveCreatesAFile() {
		SaveFile file = new SaveFile();
		file.save();
		// assert that file is created
	}

}
