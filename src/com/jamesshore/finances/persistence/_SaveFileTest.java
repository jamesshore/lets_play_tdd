package com.jamesshore.finances.persistence;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;
import org.junit.rules.*;

public class _SaveFileTest {

	private File path;
	private SaveFile saveFile;

	@Rule
	public TemporaryFolder tempDir = new TemporaryFolder();

	@Before
	public void setup() {
		path = new File(tempDir.getRoot(), "testfile");
		saveFile = new SaveFile(path);
	}

	@Test
	public void saveCreatesAFile() throws IOException {
		assertFalse("assume test file does not exist", path.exists());

		saveFile.save();
		assertTrue("file should now exist", path.exists());
		assertEquals("file size", 0, path.length());
	}

	@Test
	public void saveOverwritesAnExistingFile() throws IOException {
		FileWriter writer = new FileWriter(path);
		writer.write("test");
		writer.close(); // TODO: exception handling
		assertEquals("file size setup assumption", 4, path.length());

		saveFile.save();
		assertEquals("file should have been overwritten", 0, path.length());
	}

	@Test
	public void saveWritesFingerprint() throws IOException {
		saveFile.save();

		assertEquals("com.jamesshore.finances,1", saveFileContents());
	}

	@Test
	public void saveRecordsStartingBalance() {
		// saveFile.save(startingBalance);
		//
		// assertEquals("10000", saveFile.contents())
	}

	private String saveFileContents() throws IOException {
		BufferedReader input = new BufferedReader(new FileReader(path));
		try {
			StringBuffer result = new StringBuffer();
			for (int c = input.read(); c != -1; c = input.read()) {
				result.append((char)c);
			}
			return result.toString();
		}
		finally {
			input.close();
		}
	}
}
