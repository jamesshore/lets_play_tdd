package com.jamesshore.finances.ui;

import javax.swing.*;
import net.miginfocom.swing.*;
import com.jamesshore.finances.ui.DollarsTextField.ChangeListener;

public class ConfigurationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final ApplicationModel applicationModel;

	public ConfigurationPanel(ApplicationModel applicationModel) {
		this.applicationModel = applicationModel;
		this.setLayout(new MigLayout("fillx, wrap 2", "[right]rel[grow]"));
		this.add(new JLabel("Starting Balance:"));
		this.add(startingBalanceField(), "growx");
		this.add(new JLabel("Cost Basis:"));
		this.add(costBasisField(), "growx");
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
		return new DollarsTextField(applicationModel.startingCostBasis());
	}
}
