package com.jamesshore.finances.ui;

import java.io.*;
import com.jamesshore.finances.util.*;

public class __ApplicationModelSpy extends ApplicationModel {
	public boolean configurationUpdatedCalled = false;

	public void save(File saveFile) throws IOException {
		throw new UnreachableCodeException();
	}

	@Override
	public void configurationUpdated() {
		configurationUpdatedCalled = true;
	}
}