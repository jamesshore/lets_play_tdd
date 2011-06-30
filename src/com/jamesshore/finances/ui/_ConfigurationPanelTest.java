package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
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
		MigLayout manager = (MigLayout)panel.getLayout();
		assertEquals("layout", MigLayout.class, manager.getClass());
		assertEquals("layout constraints", "fillx", manager.getLayoutConstraints());
		assertEquals("column constraints", "[grow]", manager.getColumnConstraints());

		assertEquals("# of components", 1, panel.getComponents().length);
		assertEquals("starting balance field", DollarsTextField.class, startingBalanceField().getClass());
		assertEquals("starting balance field constraint", "growx", manager.getComponentConstraints(startingBalanceField()));
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
