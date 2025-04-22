package net.twonibbles.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import net.twonibbles.callbacks.GameEventsListener;
import net.twonibbles.constants.Constants;
import net.twonibbles.image.Image;
import net.twonibbles.image.ImageFactory;
import net.twonibbles.model.Bomb;
import net.twonibbles.model.EnemyShip;
import net.twonibbles.model.Laser;
import net.twonibbles.model.SpaceShip;

public class GamePanel extends JPanel{
	
	private ImageIcon backgroundImage;
	private Timer timer;
	private SpaceShip spaceShip;
	private Laser laser;
	private boolean inGame = true;
	private int direction = -1;
	private List<EnemyShip> enemyShips;
	private List<Bomb> bomb;
	private Random generator;
	private String message;
	private int deaths;
	
	public GamePanel() {
		initializeVariables();
		initializeLayout();
		initializeGame();
	}

	private void initializeGame() {
		
		for(int i = 0; i<Constants.ENEMY_SHIPS_ROW; i++) {
			for(int j = 0; j<Constants.ENEMY_SHIPS_COLUMN; j++) {
				EnemyShip enemyShip = new EnemyShip(Constants.ENEMY_SHIP_INT_X + 50 * j, Constants.ENEMY_SHIP_INT_Y +50 * i);
				this.enemyShips.add(enemyShip);
			}
		}
		
	}

	private void initializeVariables() {
		this.generator = new Random();
		this.bomb = new ArrayList<>();
		this.enemyShips = new ArrayList<>();
		this.spaceShip = new SpaceShip();
		this.laser = new Laser();
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
	
	private void drawLaser(Graphics g) {
		if(!laser.isDead()) {
		g.drawImage(laser.getImage(), laser.getX(), laser.getY(), this);
		}
	}
	
	private void drawAliens(Graphics g) {
		
		for(EnemyShip enemyShip : this.enemyShips)
			if(enemyShip.isVisible()) {
				g.drawImage(enemyShip.getImage(), enemyShip.getX(), enemyShip.getY(), this);
			}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage.getImage(), 0, 0,Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT, null);
		doDrawing(g);
	}

	private void doDrawing(Graphics g) {
		
		if(inGame) {
			drawPlayer(g);
			drawLaser(g);
			drawAliens(g);
			drawBombs(g);
			
		} else {
			if(timer.isRunning()) {
				timer.stop();
			}
			
			drawGameOver(g);
		}
		
		Toolkit.getDefaultToolkit().sync();
		
	}

	private void drawGameOver(Graphics g) {
		
		g.drawImage(backgroundImage.getImage(), 0, 0, null);
		
		Font font = new Font("Helvatica", Font.BOLD,50);
		FontMetrics  fontMetrics = this.getFontMetrics(font);
		
		g.setColor(Color.white);
		g.setFont(font);
		g.drawString(message, (Constants.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Constants.BOARD_HEIGHT / 2 - 100);
		
	}

	private void drawBombs(Graphics g) {
		for(Bomb bomb : this.bomb)
			if(!bomb.isDead())
				g.drawImage(bomb.getImage(), bomb.getX(), bomb.getY(), this);
		
		
	}

	private void drawAliens() {
		// TODO Auto-generated method stub
		
	}

	public void doOneLoop() {
		update();
		repaint();		
	}

	private void update() {
		// Game Over
		if(spaceShip.isDead()) {
			inGame = false;
			message = Constants.GAME_OVER;
		}
		
		// player wins.
		if(deaths == this.enemyShips.size()) { 
			inGame = false;
			message = Constants.WIN;
		}
		
		this.spaceShip.move();
		
		if(!laser.isDead()) {
			
			int shotX = laser.getX();
			int shotY = laser.getY();
			
			for(EnemyShip alien : this.enemyShips) {
				int alienX = alien.getX();
				int alienY = alien.getY();
				
				if(!alien.isVisible()) continue;
				
				// collison dection statement
				
				if(shotX >= (alienX) && shotX <= (alienX + Constants.ENEMY_SHIP_WIDTH) 
						&& shotY >= (alienY) && shotY <= (alienY + Constants.ENEMY_SHIP_HEIGHT)) {
					alien.setVisible(false);
					laser.die();
					deaths++;
				}
			}
				
			this.laser.move();
			
		}
		
		
		for(EnemyShip enemyShip : this.enemyShips) {
			if(enemyShip.getX() >= Constants.BOARD_WIDTH - 2 * Constants.BOARDER_PADDING && direction != -1 || enemyShip.getX() <= Constants.BOARDER_PADDING && direction != 1) {
				direction *= -1;
				Iterator<EnemyShip> ufoIterator = enemyShips.iterator();
				
				while(ufoIterator.hasNext()) {
					EnemyShip ufo = ufoIterator.next();
					ufo.setY(ufo.getY() + Constants.GO_DOWN);
				}
			}
						
			if(enemyShip.isVisible()) {
				
				if(enemyShip.getY() > Constants.BOARD_HEIGHT - 100 - Constants.SPACESHIP_HEIGHT) {
					spaceShip.die();
				}
				
				enemyShip.move(direction);
			}
		}
		
		//Bombs are generated
		for(EnemyShip enemyShip : this.enemyShips) {
			if(enemyShip.isVisible() && generator.nextDouble() < Constants.BOMB_DROPPING_PROBABILITY) {
				Bomb bomb = new Bomb(enemyShip.getX(), enemyShip.getY());
				this.bomb.add(bomb); 
			}
		}
		
		//move bomb
		for(Bomb bomb : bomb) {
			
			int bombX = bomb.getX();
			int bombY = bomb.getY();
			int spaceshipX = spaceShip.getX();
			int spaceshipY = spaceShip.getY();
			
			if(!bomb.isDead() && !spaceShip.isDead()){
				if(bombX >= (spaceshipX) && bombX <= (spaceshipX + Constants.ENEMY_SHIP_WIDTH) 
						&& bombY >= (spaceshipY) && bombY <= (spaceshipY + Constants.ENEMY_SHIP_HEIGHT)) {
					bomb.die();
					spaceShip.die();
				}
			}
			
			if(!bomb.isDead()) {
				bomb.move();
			}
		}
	}
	
	
	public void keyPressed(KeyEvent e) {
		this.spaceShip.keyPressed(e);
		
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_SPACE) {
			int laserX = this.spaceShip.getX();
			int laserY = this.spaceShip.getY();
			
			if(inGame && laser.isDead()) {
				laser = new Laser(laserX, laserY);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		this.spaceShip.keyReleased(e);
	}

}
