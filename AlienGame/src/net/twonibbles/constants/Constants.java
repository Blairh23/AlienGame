package net.twonibbles.constants;

public class Constants {
	
	private Constants() {
		
	}
	
	public static final String TITLE = "Space Invasion";
	
	public static final int BOARD_WIDTH = 900;
	public static final int BOARD_HEIGHT = 750;
	
	//Speed of the application
	public static final int GAME_SPEED = 10;
	public static final int SPACESHIP_WIDTH = 34;
	public static final int SPACESHIP_HEIGHT = 28;
	// UFO constants
	public static final int ENEMY_SHIP_HEIGHT = 24;
	public static final int ENEMY_SHIP_WIDTH = 32;
	public static final int ENEMY_SHIP_INT_X = 280;
	public static final int ENEMY_SHIP_INT_Y = 100;
	public static final int ENEMY_SHIPS_ROW = 4;
	public static final int ENEMY_SHIPS_COLUMN = 8;
	// images for objects
	public static final String UFO_IMAGE_URL = "Images/ufo.png";
	public static final String LASER_IMAGE_URL = "Images/laser.png";
	public static final String BOMB_IMAGE_URL = "Images/bomb.png";
	public static final String BACKGROUND_IMAGE_URL = "Images/background.jpg";
	public static final String SPACESHIP_IMAGE_URL = "Images/spaceship.png";
	// speed of the lazar
	public static final int LASER_HORIZONTAL_TRANSLATION = 4;
	
}
