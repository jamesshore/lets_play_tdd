package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.util.*;
import javax.swing.*;

abstract class __Invocation {
	abstract public void invoke();

	abstract boolean stopWaitingWhen();

	public static void invokeAndWaitFor(String message, int timeout, final __Invocation check) {
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