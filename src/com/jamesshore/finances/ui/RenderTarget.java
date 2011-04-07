package com.jamesshore.finances.ui;

import java.awt.*;
import javax.swing.*;

public interface RenderTarget {

	void setText(String text);

	void setIcon(Icon icon);

	void setToolTipText(String text);

	void setForegroundColor(Color color);

}
