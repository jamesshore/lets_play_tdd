package com.jamesshore.finances.ui;

import java.io.*;

public class __ApplicationModelSpy extends ApplicationModel {
	public boolean configurationUpdatedCalled = false;
	public File saveCalledWith; // TODO: Delete me?

	@Override
	public void save(File saveFile) throws IOException {
		saveCalledWith = saveFile;
	}

	@Override
	public void configurationUpdated() {
		configurationUpdatedCalled = true;
	}
}