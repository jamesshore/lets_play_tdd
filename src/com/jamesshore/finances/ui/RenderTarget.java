package com.jamesshore.finances.ui;

import java.awt.*;
import javax.swing.*;

public interface RenderTarget {

	void setText(String text);

	void setIcon(Icon icon, String toolTipText);

	void setForegroundColor(Color color);

}
