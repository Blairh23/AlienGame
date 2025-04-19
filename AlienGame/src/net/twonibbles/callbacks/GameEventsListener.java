package net.twonibbles.callbacks;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import net.twonibbles.ui.GamePanel;

public class GameEventsListener extends KeyAdapter {
	
	private GamePanel board;
	
	public GameEventsListener(GamePanel board) {
		this.board = board;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.board.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.board.keyReleased(e);
	}
	
	

}
