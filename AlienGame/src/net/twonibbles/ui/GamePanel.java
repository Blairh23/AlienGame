package net.twonibbles.ui;

import java.awt.Dimension;

import javax.swing.JPanel;

import net.twonibbles.constants.Constants;

public class GamePanel extends JPanel{
	
	public GamePanel() {
		initializeLayout();
	}

	private void initializeLayout() {
		
		setPreferredSize(new Dimension(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT));
		
		
	}

}
