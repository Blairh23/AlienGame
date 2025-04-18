package net.twonibbles.ui;

import javax.swing.JFrame;

import net.twonibbles.constants.Constants;

public class GameMainFrame extends JFrame {
	

	public GameMainFrame() {
		
		initializeLayout();
		
	}

	private void initializeLayout() {
		
		add(new GamePanel());
		
		setTitle(Constants.TITLE);
		
		pack();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		
	}

}
