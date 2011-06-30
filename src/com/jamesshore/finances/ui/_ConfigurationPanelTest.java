package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;
import org.junit.*;
import com.jamesshore.finances.domain.*;

public class _ConfigurationPanelTest {

	private ConfigurationPanel panel;
	private ApplicationModel model;

	private DollarsTextField startingBalanceField() {
		return (DollarsTextField)panel.getComponent(1);
	}

	private DollarsTextField costBasisField() {
		return (DollarsTextField)panel.getComponent(3);
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
		assertEquals("layout constraints", "fillx, wrap 2", manager.getLayoutConstraints());
		assertEquals("column constraints", "[right]rel[grow]", manager.getColumnConstraints());

		Component[] components = panel.getComponents();
		assertEquals("# of components", 4, components.length);
		assertEquals("starting balance label", JLabel.class, components[0].getClass());
		assertEquals("starting balance field", DollarsTextField.class, startingBalanceField().getClass());
		assertEquals("starting balance field constraint", "growx", manager.getComponentConstraints(startingBalanceField()));
		assertEquals("cost basis label", JLabel.class, components[2].getClass());
		assertEquals("cost basis field", DollarsTextField.class, costBasisField().getClass());
		assertEquals("cost basis field constraint", "growx", manager.getComponentConstraints(costBasisField()));
	}

	@Test
	public void fieldsInitializeToModelValue() {
		assertEquals("starting balance field text", model.startingBalance(), startingBalanceField().getDollars());
		assertEquals("cost basis field text", model.startingCostBasis(), costBasisField().getDollars());
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
