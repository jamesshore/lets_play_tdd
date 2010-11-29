package com.jamesshore.finances.ui;

import java.awt.*;
import javax.swing.*;

public class ApplicationFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static final String TITLE = "Financial Projector";
	public static final Point INITIAL_POSITION = new Point(400, 300);
	public static final Dimension INITIAL_SIZE = new Dimension(900, 400);

	public ApplicationFrame() {
		super(TITLE);
		setLocation(INITIAL_POSITION);
		setSize(INITIAL_SIZE);
		getContentPane().add(new JPanel());
	}
	
}
