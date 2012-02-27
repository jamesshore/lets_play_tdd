package com.jamesshore.finances.values;

import java.awt.*;
import javax.swing.*;
import com.jamesshore.finances.ui.*;

class __RenderTargetStub implements RenderTarget {
	public String text;
	public Icon icon;
	public String toolTipText;
	public Color foregroundColor;

	public void setText(String text) {
		this.text = text;
	}

	public void setIcon(Icon icon, String toolTipText) {
		this.icon = icon;
		this.toolTipText = toolTipText;
	}

	public void setForegroundColor(Color color) {
		this.foregroundColor = color;
	}
}
