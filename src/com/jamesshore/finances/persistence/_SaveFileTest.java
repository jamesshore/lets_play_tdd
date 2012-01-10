package com.jamesshore.finances.persistence;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;
import org.junit.rules.*;
import com.jamesshore.finances.domain.*;

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

		saveFile.save(new InvalidDollars());
		assertTrue("file should now exist", path.exists());
	}

	@Test
	public void saveOverwritesAnExistingFile() throws IOException {
		writeFile("test");
		assertEquals("file size setup assumption", 4, path.length());

		saveFile.save(new InvalidDollars());
		String fileContents = readFile();
		assertFalse("file should have been overwritten", fileContents.startsWith("test"));
	}

	@Test
	public void saveWritesFileContents() throws IOException {
		saveFile.save(ValidDollars.create(1.23));// , ValidDollars.create(10.24), ValidDollars.create(100.25));

		String expected = "com.jamesshore.finances,1\n$1.23\n";
		assertEquals(expected, readFile());
	}

	private String readFile() throws IOException {
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

	private void writeFile(String text) throws IOException {
		Writer writer = new BufferedWriter(new FileWriter(path));
		try {
			writer.write(text);
		}
		finally {
			writer.close();
		}
	}
}
