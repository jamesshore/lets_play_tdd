package com.jamesshore.finances.ui;

public class __ApplicationModelSpy extends ApplicationModel {
	public boolean configurationUpdatedCalled = false;

	@Override
	public void configurationUpdated() {
		configurationUpdatedCalled = true;
	}
}