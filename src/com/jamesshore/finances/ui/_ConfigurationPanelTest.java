package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.awt.*;
import net.miginfocom.swing.*;
import org.junit.*;
import com.jamesshore.finances.domain.*;

public class _ConfigurationPanelTest {

	private ConfigurationPanel panel;
	private ApplicationModel model;

	private DollarsTextField startingBalanceField() {
		return (DollarsTextField)panel.getComponent(0);
	}

	@Before
	public void setUp() {
		model = new ApplicationModel();
		panel = new ConfigurationPanel(model);
	}

	@Test
	public void layout() {
		assertEquals("layout", MigLayout.class, panel.getLayout().getClass());

		Component[] components = panel.getComponents();
		assertEquals("# of components", 1, components.length);
		assertEquals("starting balance field", DollarsTextField.class, components[0].getClass());
	}

	@Test
	public void startingBalanceInitializesToModelsValue() {
		assertEquals("starting balance field text", model.startingBalance(), startingBalanceField().getDollars());
	}

	@Test
	public void startingBalanceFieldUpdatesApplicationModel() {
		class MockApplicationModel extends ApplicationModel {
			public Dollars setStartingBalanceCalledWith;

			@Override
			public void setStartingBalance(Dollars startingBalance) {
				setStartingBalanceCalledWith = startingBalance;
			}
		}
		MockApplicationModel mockModel = new MockApplicationModel();
		panel = new ConfigurationPanel(mockModel);

		startingBalanceField().setText("668");
		assertEquals("applicationModel should be updated", ValidDollars.create(668), mockModel.setStartingBalanceCalledWith);
	}
}
