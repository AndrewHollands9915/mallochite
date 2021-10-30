package ui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class MessagePanel extends JPanel {

	JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	MessagePanel(){
		panel.setBackground(new Color(88,238,148));
    	panel.setBounds(100, 100, 400, 30);
	}
	 
	
}
