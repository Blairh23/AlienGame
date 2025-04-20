package net.twonibbles.model;

import javax.swing.ImageIcon;

import net.twonibbles.constants.Constants;
import net.twonibbles.image.Image;
import net.twonibbles.image.ImageFactory;

public class EnemyShip extends Sprite{
	
		private boolean visible = true;
		
		public EnemyShip(int x, int y) {
			this.x = x;
			this.y = y;
			initialize();
		}

		private void initialize() {
			ImageIcon imageIcon = ImageFactory.createImage(Image.UFO);
			setImage(imageIcon.getImage());	
		}

		public boolean isVisible() {
			return visible;
		}

		public void setVisible(boolean visible) {
			this.visible = visible;
		}

		public void move(int direction) {
			this.x += direction;
		}

		@Override
		public void move() {
			// TODO Auto-generated method stub
			
		}
		
		
		
}
