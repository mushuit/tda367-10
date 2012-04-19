package tetrix.core;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Square {
	private Position pos;
	
	public Square(Position pos) {
		this.pos = pos;
		startUp();
	}
	
	private void startUp(){
		
	}
	
	public Position getPos(){
		return new Position(pos);
	}
	
	public void setPos(Position pos){
		this.pos = pos;
	}
	
	public void setY(float f){
		pos.setY(f);
	}

	public float getY(){
		return pos.getY();
	}
}
