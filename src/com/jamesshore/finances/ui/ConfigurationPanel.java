package com.jamesshore.finances.ui;

import javax.swing.*;
import net.miginfocom.swing.*;
import com.jamesshore.finances.ui.DollarsTextField.ChangeListener;

public class ConfigurationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final ApplicationModel applicationModel;

	public ConfigurationPanel(ApplicationModel applicationModel) {
		this.applicationModel = applicationModel;
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
		final DollarsTextField field = new DollarsTextField(applicationModel.startingBalance());
		field.addTextChangeListener(new ChangeListener() {
			public void textChanged() {
				applicationModel.setStartingBalance(field.getDollars());
			}
		});
		return field;
	}

	private DollarsTextField costBasisField() {
		final DollarsTextField field = new DollarsTextField(applicationModel.startingCostBasis());
		field.addTextChangeListener(new ChangeListener() {
			public void textChanged() {
				applicationModel.setStartingCostBasis(field.getDollars());
			}
		});
		return field;
	}

	private DollarsTextField yearlySpendingField() {
		final DollarsTextField field = new DollarsTextField(applicationModel.yearlySpending());
		field.addTextChangeListener(new ChangeListener() {
			public void textChanged() {
				applicationModel.setYearlySpending(field.getDollars());
			}
		});
		return field;
	}
}
