package net.twonibbles.ui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import net.twonibbles.constants.Constants;
import net.twonibbles.image.Image;
import net.twonibbles.image.ImageFactory;

public class GamePanel extends JPanel{
	
	private ImageIcon backgroundImage;
	private Timer timer;
	
	public GamePanel() {
		initializeVariables();
		initializeLayout();
	}

	private void initializeVariables() {
		this.backgroundImage = ImageFactory.createImage(Image.BACKGROUND);	
		this.timer = new Timer(Constants.GAME_SPEED, new GameLoop(this));
		this.timer.start();
	}

	private void initializeLayout() {
		setPreferredSize(new Dimension(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT));
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage.getImage(), 0, 0,Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT, null);
		System.out.println("REPAINT");
	}

	public void doOneLoop() {
		update();
		repaint();		
	}

	private void update() {
		System.out.println("UPDATE");
		
	}

}
