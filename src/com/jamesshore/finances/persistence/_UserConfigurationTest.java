package com.jamesshore.finances.persistence;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;
import org.junit.rules.*;
import com.jamesshore.finances.values.*;

public class _UserConfigurationTest {

	private File path;
	private UserConfiguration saveFile;
	private UserEnteredDollars anyValue = new UserEnteredDollars("any");

	@Rule
	public TemporaryFolder tempDir = new TemporaryFolder();

	@Before
	public void setup() {
		path = new File(tempDir.getRoot(), "testfile");
		saveFile = new UserConfiguration(path);
	}

	@Test
	public void canRetrieveFileObject() {
		assertEquals(path, saveFile.path());
	}

	@Test
	public void hasSaved() throws IOException {
		assertFalse("should not be saved before save() called", saveFile.hasEverBeenSaved());
		doSave(anyValue, anyValue, anyValue);
		assertTrue("should be saved after save() called", saveFile.hasEverBeenSaved());
	}

	@Test
	public void hasSavedIsNotTrueIfExceptionOccurredWhileSaving() {
		try {
			path.createNewFile();
			path.setWritable(false);
			doSave(anyValue, anyValue, anyValue);
			fail("expected IOException");
		}
		catch (IOException e) {
			assertFalse("should not be saved", saveFile.hasEverBeenSaved());
		}
		finally {
			path.setWritable(true);
		}
	}

	@Test
	public void saveCreatesAFile() throws IOException {
		assertFalse("assume test file does not exist", path.exists());

		doSave(anyValue, anyValue, anyValue);
		assertTrue("file should now exist", path.exists());
	}

	@Test
	public void saveOverwritesAnExistingFile() throws IOException {
		writeFile("test");
		assertEquals("file size setup assumption", 4, path.length());

		doSave(anyValue, anyValue, anyValue);
		String fileContents = readFile();
		assertFalse("file should have been overwritten", fileContents.startsWith("test"));
	}

	@Test
	public void saveWritesFileContents() throws IOException {
		doSave(new UserEnteredDollars("1.23"), new UserEnteredDollars("10.24"), new UserEnteredDollars("100.25"));
		assertFileMatches("1.23", "10.24", "100.25");
	}

	@Test
	public void saveWritesOutUserEnteredValuesExactlyAsEntered() throws IOException {
		doSave(new UserEnteredDollars("foo"), new UserEnteredDollars("  bar"), new UserEnteredDollars("baz\t"));
		assertFileMatches("foo", "  bar", "baz\t");
	}

	@Test
	public void saveHandlesDelimitersInUserInput() throws IOException {
		doSave(new UserEnteredDollars("\n\n\n \\n"), anyValue, anyValue);
		assertFileMatches("\\n\\n\\n \\\\n", "any", "any");
	}

	private void doSave(UserEnteredDollars startingBalance, UserEnteredDollars costBasis, UserEnteredDollars yearlySpending) throws IOException {
		saveFile.save(startingBalance, costBasis, yearlySpending);
	}

	private void assertFileMatches(String expectedStartingBalance, String expectedCostBasis, String expectedYearlySpending) throws IOException {
		assertFileMatches("file", expectedStartingBalance, expectedCostBasis, expectedYearlySpending);
	}

	private void assertFileMatches(String message, String expectedStartingBalance, String expectedCostBasis, String expectedYearlySpending) throws IOException {
		String expected = "com.jamesshore.finances,1\n";
		expected += expectedStartingBalance + "\n";
		expected += expectedCostBasis + "\n";
		expected += expectedYearlySpending + "\n";
		assertEquals(message, expected, readFile());

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