package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;

public class _SaveAsDialogTest {

	private __ApplicationModelSpy mockModel = new __ApplicationModelSpy();
	private SaveAsDialog dialog = new SaveAsDialog(null, mockModel);

	@Test
	public void saveAsDialogShouldTellApplicationModelToSaveWhenSaveButtonPushed() {
		dialog.setDirectory("/example");
		dialog.setFile("filename");
		dialog.doSave();
		assertEquals("applicationModel should be told to save", new File("/example/filename"), mockModel.saveCalledWith);
	}

	@Test
	public void saveAsDialogShouldDoNothingWhenCancelButtonPushed() {
		dialog.setDirectory(null);
		dialog.setFile(null);
		dialog.doSave();
		assertNull("applicationModel should not have been told to save", mockModel.saveCalledWith);
	}

}
