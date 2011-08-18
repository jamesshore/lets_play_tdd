package com.jamesshore.finances.ui;

import java.awt.*;
import javax.swing.*;

class LabelRenderTargetAdapter implements RenderTarget {
	private JLabel label;

	public LabelRenderTargetAdapter(JLabel label) {
		this.label = label;
	}

	@Override
	public void setText(String text) {
		label.setText(text);
	}

	@Override
	public void setIcon(Icon icon, String toolTipText) {
		label.setIcon(icon);
		label.setToolTipText(toolTipText);
	}

	@Override
	public void setForegroundColor(Color color) {
		label.setForeground(color);
	}

}