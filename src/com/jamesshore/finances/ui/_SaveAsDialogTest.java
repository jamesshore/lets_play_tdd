package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import org.junit.*;

public class _SaveAsDialogTest {

	private __ApplicationModelSpy mockModel = new __ApplicationModelSpy();
	private SaveAsDialog dialog = new SaveAsDialog(null, mockModel);

	@After
	public void teardown() {
		dialog.dispose();
	}

	@Test
	public void saveAsDialogShouldTellApplicationModelToSaveWhenSaveButtonPushed() {
		doSave(dialog, "/example", "filename");
		assertEquals("applicationModel should be told to save", new File("/example/filename"), mockModel.saveCalledWith);
	}

	@Test
	public void saveAsDialogShouldDoNothingWhenCancelButtonPushed() {
		doSave(dialog, null, null);
		assertNull("applicationModel should not have been told to save", mockModel.saveCalledWith);
	}

	@Test
	public void saveAsDialogShouldHandleSaveExceptionsGracefully() {
		final Frame frame = new Frame();
		final SaveAsDialog exceptionThrowingDialog = createExceptionThrowingSaveAsDialog(frame);

		invokeAndWaitFor("warning dialog", 1000, new Invocation() {
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
		final ApplicationModel exceptionThrower = new __ApplicationModelSpy() {
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

	// TODO: Eliminate Invocation/invokeAndWaitFor() duplication between SaveAsDialogTest & ApplicationFrameTest
	abstract class Invocation {
		abstract public void invoke();

		abstract boolean stopWaitingWhen();
	}

	private void invokeAndWaitFor(String message, int timeout, final Invocation check) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				check.invoke();
			}
		});

		long startTime = new Date().getTime();
		while (!check.stopWaitingWhen()) {
			Thread.yield();
			long elapsedMilliseconds = new Date().getTime() - startTime;
			if (elapsedMilliseconds > timeout) fail("expected " + message + " within " + timeout + " milliseconds");
		}
	}

}
