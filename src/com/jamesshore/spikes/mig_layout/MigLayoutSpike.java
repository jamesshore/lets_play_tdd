package com.jamesshore.spikes.mig_layout;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

public class MigLayoutSpike extends JFrame {
	private static final long serialVersionUID = 1L;

	public MigLayoutSpike() {
		setTitle("MiGLayout Spike");
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addComponents();
		pack();
		this.setLocation(400, 200);
		this.setSize(600, 300);
	}

	private void addComponents() {
		Container content = this.getContentPane();
		content.setLayout(new MigLayout("fillx, wrap 6", "[right]rel[grow]unrel[right]rel[grow]unrel[right]rel[grow]"));

		addField(content, "Starting Balance:");
		addField(content, "Starting Year:");
		addField(content, "Interest Rate:");
		addField(content, "Cost Basis:");
		addField(content, "Ending Year:");
		addField(content, "Tax Rate:");
		addField(content, "Yearly Spending:");
	}

	private void addField(Container content, String label) {
		content.add(new JLabel(label), "right");
		content.add(new JTextField(), "growx, sizegroup");
	}

	public static void main(String[] args) {
		new MigLayoutSpike().setVisible(true);
	}

}
