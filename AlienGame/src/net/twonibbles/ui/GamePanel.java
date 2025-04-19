package net.twonibbles.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import net.twonibbles.callbacks.GameEventsListener;
import net.twonibbles.constants.Constants;
import net.twonibbles.image.Image;
import net.twonibbles.image.ImageFactory;
import net.twonibbles.model.SpaceShip;

public class GamePanel extends JPanel{
	
	private ImageIcon backgroundImage;
	private Timer timer;
	private SpaceShip spaceShip;
	private boolean inGame = true;
	
	public GamePanel() {
		initializeVariables();
		initializeLayout();
	}

	private void initializeVariables() {
		this.spaceShip = new SpaceShip();
		this.backgroundImage = ImageFactory.createImage(Image.BACKGROUND);	
		this.timer = new Timer(Constants.GAME_SPEED, new GameLoop(this));
		this.timer.start();
	}

	private void initializeLayout() {
		addKeyListener(new GameEventsListener(this));
		setFocusable(true);
		setPreferredSize(new Dimension(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT));
	}
	
	private void drawPlayer(Graphics g) {
		g.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), this);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage.getImage(), 0, 0,Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT, null);
		doDrawing(g);
	}

	private void doDrawing(Graphics g) {
		
		if(inGame) {
			drawPlayer(g);
		} else {
			if(timer.isRunning()) {
				timer.stop();
			}
		}
		
		Toolkit.getDefaultToolkit().sync();
		
	}

	public void doOneLoop() {
		update();
		repaint();		
	}

	private void update() {
		this.spaceShip.move();
	}

	public void keyPressed(KeyEvent e) {
		this.spaceShip.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		this.spaceShip.keyReleased(e);
	}

}
