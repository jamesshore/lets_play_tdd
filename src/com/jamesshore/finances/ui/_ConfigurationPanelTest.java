package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;
import org.junit.*;
import com.jamesshore.finances.persistence.*;
import com.jamesshore.finances.values.*;

public class _ConfigurationPanelTest {

	private ConfigurationPanel panel;
	private UserConfiguration userConfiguration;

	private DollarsTextField startingBalanceField() {
		return (DollarsTextField)panel.getComponent(1);
	}

	private DollarsTextField costBasisField() {
		return (DollarsTextField)panel.getComponent(3);
	}

	private DollarsTextField yearlySpendingField() {
		return (DollarsTextField)panel.getComponent(5);
	}

	@Before
	public void setUp() {
		ApplicationModel model = new ApplicationModel();
		userConfiguration = model.userConfiguration();
		panel = new ConfigurationPanel(userConfiguration);
	}

	@Test
	public void layout() {
		MigLayout manager = (MigLayout)panel.getLayout();
		assertEquals("layout", MigLayout.class, manager.getClass());
		assertEquals("layout constraints", "fillx, wrap 2", manager.getLayoutConstraints());
		assertEquals("column constraints", "[right]rel[grow]", manager.getColumnConstraints());

		Component[] components = panel.getComponents();
		assertEquals("# of components", 6, components.length);
		assertFormField("starting balance", components[0], components[1]);
		assertFormField("cost basis", components[2], components[3]);
		assertFormField("yearly spending", components[4], components[5]);
	}

	private void assertFormField(String message, Component label, Component field) {
		MigLayout manager = (MigLayout)panel.getLayout();
		assertEquals(message + " label", JLabel.class, label.getClass());
		assertEquals(message + " field", DollarsTextField.class, field.getClass());
		assertEquals(message + " field constraint", "growx", manager.getComponentConstraints(field));
	}

	@Test
	public void fieldsInitializeToModelValue() {
		assertEquals("starting balance field text", userConfiguration.getStartingBalance(), startingBalanceField().getDollars());
		assertEquals("cost basis field text", userConfiguration.getStartingCostBasis(), costBasisField().getDollars());
		assertEquals("yearly spending field text", userConfiguration.getYearlySpending(), yearlySpendingField().getDollars());
	}

	@Test
	public void startingBalanceFieldUpdatesModel() {
		startingBalanceField().setText("668");
		assertEquals("user configuration should be updated", new ValidDollars(668), userConfiguration.getStartingBalance());
	}

	@Test
	public void costBasisFieldUpdatesModel() {
		costBasisField().setText("670");
		assertEquals("user configuration should be updated", new ValidDollars(670), userConfiguration.getStartingCostBasis());
	}

	@Test
	public void yearlySpendingFieldUpdatesModel() {
		yearlySpendingField().setText("672");
		assertEquals("user configuration should be updated", new ValidDollars(672), userConfiguration.getYearlySpending());
	}

}
