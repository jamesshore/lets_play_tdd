package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import org.junit.*;
import com.jamesshore.finances.persistence.*;

public class _SaveAsDialogTest {

	private ApplicationModel model = new ApplicationModel();
	private SaveAsDialog dialog = new SaveAsDialog(null, model);

	@Before
	public void setup() {
		UserConfiguration.STUB_OUT_FILE_SYSTEM_FOR_TESTING_ONLY = true;
	}

	@After
	public void teardown() {
		UserConfiguration.STUB_OUT_FILE_SYSTEM_FOR_TESTING_ONLY = false;
		dialog.dispose();
	}

	@Test
	public void layout() {
		assertEquals("Save As dialog mode should be 'save'", FileDialog.SAVE, dialog.getMode());
		assertEquals("Save As dialog title", "Save As", dialog.getTitle());
	}

	@Test
	public void saveAsDialogShouldTellApplicationModelToSaveWhenSaveButtonPushed() {
		doSave(dialog, "/example", "filename");
		assertEquals("applicationModel should be told to save", new File("/example/filename"), model.lastSavedPathOrNullIfNeverSaved());
	}

	@Test
	public void saveAsDialogShouldDoNothingWhenCancelButtonPushed() {
		doSave(dialog, null, null);
		assertNull("applicationModel should not have been told to save", model.lastSavedPathOrNullIfNeverSaved());
	}

	@Test
	public void saveAsDialogShouldHandleSaveExceptionsGracefully() {
		final Frame frame = new Frame();
		final SaveAsDialog exceptionThrowingDialog = createExceptionThrowingSaveAsDialog(frame);

		__Invocation.invokeAndWaitFor("warning dialog", 1000, new __Invocation() {
			@Override
			public void invoke() {
				doSave(exceptionThrowingDialog, "/example", "filename");
			}

			@Override
			public boolean stopWaitingWhen() {
				Dialog dialog = warningDialogOrNullIfNotFound(frame);
				return dialog != null && dialog.isVisible();
			}
		});

		JDialog dialogWindow = (JDialog)warningDialogOrNullIfNotFound(frame);
		JOptionPane dialogPane = (JOptionPane)dialogWindow.getContentPane().getComponent(0);
		assertEquals("Warning dialog parent", frame, dialogWindow.getParent());
		assertEquals("Warning dialog title", "Save File", dialogWindow.getTitle());
		assertEquals("Warning dialog message", "Could not save file: generic exception", dialogPane.getMessage());
		assertEquals("Warning dialog type should be 'warning'", JOptionPane.WARNING_MESSAGE, dialogPane.getMessageType());
	}

	private SaveAsDialog createExceptionThrowingSaveAsDialog(Frame frame) {
		final ApplicationModel exceptionThrower = new ApplicationModel() {
			@Override
			public void save(File saveFile) throws IOException {
				throw new IOException("generic exception");
			}
		};

		return new SaveAsDialog(frame, exceptionThrower);
	}

	private Dialog warningDialogOrNullIfNotFound(Frame frame) {
		Window[] childWindows = frame.getOwnedWindows();
		if (childWindows.length < 2) return null;
		return (Dialog)childWindows[1];
	}

	private void doSave(SaveAsDialog exceptionThrowingDialog, String directory, String filename) {
		exceptionThrowingDialog.setDirectory(directory);
		exceptionThrowingDialog.setFile(filename);
		exceptionThrowingDialog.doSave();
	}

}
