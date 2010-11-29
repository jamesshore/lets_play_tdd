package com.jamesshore.finances;

import javax.swing.*;
import com.jamesshore.finances.ui.*;

public class Application {
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
        		new ApplicationFrame().setVisible(true);
            }
        });
	}
	
}
