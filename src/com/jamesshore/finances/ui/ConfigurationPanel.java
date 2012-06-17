package com.jamesshore.finances.ui;

import javax.swing.*;
import net.miginfocom.swing.*;
import com.jamesshore.finances.persistence.*;
import com.jamesshore.finances.ui.DollarsTextField.ChangeListener;

public class ConfigurationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ApplicationModel applicationModel;
	private UserConfiguration userConfiguration;

	public ConfigurationPanel(ApplicationModel applicationModel) {
		this.applicationModel = applicationModel;
		this.userConfiguration = applicationModel.userConfiguration();
		addComponents();
	}

	private void addComponents() {
		this.setLayout(new MigLayout("fillx, wrap 2", "[right]rel[grow]"));
		addField("Starting Balance:", startingBalanceField());
		addField("Cost Basis:", costBasisField());
		addField("Yearly Spending:", yearlySpendingField());
	}

	private void addField(String name, DollarsTextField field) {
		this.add(new JLabel(name));
		this.add(field, "growx");
	}

	public DollarsTextField startingBalanceField() {
		final DollarsTextField field = new DollarsTextField(userConfiguration.startingBalance);
		field.addTextChangeListener(new ChangeListener() {
			public void textChanged() {
				userConfiguration.startingBalance = field.getDollars();
				applicationModel.configurationUpdated();
			}
		});
		return field;
	}

	private DollarsTextField costBasisField() {
		final DollarsTextField field = new DollarsTextField(userConfiguration.startingCostBasis);
		field.addTextChangeListener(new ChangeListener() {
			public void textChanged() {
				userConfiguration.startingCostBasis = field.getDollars();
				applicationModel.configurationUpdated();
			}
		});
		return field;
	}

	private DollarsTextField yearlySpendingField() {
		final DollarsTextField field = new DollarsTextField(userConfiguration.yearlySpending);
		field.addTextChangeListener(new ChangeListener() {
			public void textChanged() {
				userConfiguration.yearlySpending = field.getDollars();
				applicationModel.configurationUpdated();
			}
		});
		return field;
	}
}
