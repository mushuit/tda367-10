package tetrix.core;

import org.newdawn.slick.Image;

public class Cannon{
	private float xPos;
	private float yPos;
	private int value;
	private int rotation;

	public Cannon(float xPos, float yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		rotation = 0;
		
		value = (int) xPos;
	}
	
	public float getX() {
		return xPos;
	}
	
	public float getY() {
		return yPos;
	}
	
	public void setPosition(float xPos, float yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public Position getPosition() {
		return new Position(xPos, yPos);
	}
	
	public void move(float x, float y) {
		// When Y decreases, the cannon actually moves up.
		value += x + (-y);
		
		if (value>=0 && value<=400) {
			rotation = 0;
			this.xPos += x;
		}
		else if (value>400 && value<=900) {
			rotation = -90;
			this.yPos -= y;
		}
		else if (value>900 && value<=1400) {
			rotation = 180;
		}
		else if (value>1400){ 
			rotation = 270;
		
		}
		
		if(value >= 1900) {
			value = 0;
		}
	}
	
	public int getRotation() {
		return rotation;
	}
	
	
}
