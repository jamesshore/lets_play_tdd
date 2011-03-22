package com.jamesshore.finances.ui;

import java.net.*;
import javax.swing.*;

public class Resources {

	public ImageIcon invalidDollarIcon() {
		URL iconUrl = getClass().getResource("resources/invalid_dollars.png");
		return new ImageIcon(iconUrl, "Invalid dollar amount");
	}
}
