package com.jamesshore.finances.domain;

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

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public void setToolTipText(String text) {
		this.toolTipText = text;
	}

	public void setForegroundColor(Color color) {
		this.foregroundColor = color;
	}
}
