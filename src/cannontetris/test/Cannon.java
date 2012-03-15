package cannontetris.test; 
import javax.swing.text.Position;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import cannontetris.controller.CannonController;

public class Cannon extends Image{
	private CannonController controller;

	public Cannon(Image image) throws SlickException {
		controller = new CannonController(this);
	}
	
	public Position getPosition() {
		 return controller.getPosition();
	}
	
	public void setPosition(Position pos) {
		controller.setPosition();
	}
	

}
