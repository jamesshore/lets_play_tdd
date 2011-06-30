package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.awt.*;
import javax.swing.*;
import org.junit.*;

public class _ConfigurationPanelTest {

	private JPanel panel;
	private ApplicationModel model;
	private DollarsTextField startingBalanceField;

	@Before
	public void setUp() {
		model = new ApplicationModel();
		panel = new ConfigurationPanel(model);

		Component[] components = panel.getComponents();
		startingBalanceField = (DollarsTextField)components[0];
	}

	@Test
	public void shouldLayoutProperly() {
		Component[] components = panel.getComponents();
		assertEquals("# of components", 1, components.length);
		assertEquals("starting balance field", DollarsTextField.class, components[0].getClass());
	}

	@Test
	public void startingBalanceShouldBeInitializedToModelsValue() {
		assertEquals("starting balance field text", model.startingBalance(), startingBalanceField.getDollars());
	}
}
