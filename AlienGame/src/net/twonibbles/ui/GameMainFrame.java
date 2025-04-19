package net.twonibbles.ui;

import javax.swing.JFrame;

import net.twonibbles.constants.Constants;
import net.twonibbles.image.Image;
import net.twonibbles.image.ImageFactory;

public class GameMainFrame extends JFrame {
	

	public GameMainFrame() {
		
		initializeLayout();
		
	}

	private void initializeLayout() {
		
		add(new GamePanel());
		
		setTitle(Constants.TITLE);
		// icon on top of header
		setIconImage(ImageFactory.createImage(Image.SPACESHIP).getImage());
		
		pack();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		
	}

}
