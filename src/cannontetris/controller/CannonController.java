package cannontetris.controller;

import javax.swing.text.Position;

import org.newdawn.slick.Image;

public class CannonController {
	Position pos;
	public CannonController(Image view){
		pos = new Position();
	}
	public Position getPosition(){
		return pos;
	}

	public void setPosition(Position pos){
		this.pos = pos;
	}
}
