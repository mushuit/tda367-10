package tetrix.core;

import org.newdawn.slick.Image;

public class Cannon{
	private float xPos;
	private float yPos;
	private float value;
	private int rotation;

	public Cannon(float xPos, float yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		rotation = 0;
		value = xPos;
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
	
	public void move(float direction) {
		value += direction;
		
		if (value>=0 && value<425) {
			rotation = 0;
			this.xPos += direction;
		}
		else if (value>=425 && value<900) {
			rotation = -90;
			this.yPos -= direction;
		}
		else if (value>=900 && value<1200) {
			rotation = 180;
			this.xPos -= direction;
		}
		else if (value>1200 && value<=1650){ 
			rotation = 90;
			this.yPos += direction;
		}
		
		if(value > 1650) {
			value = 0;
		}
	}
	
	public int getRotation() {
		return rotation;
	}
	
	
}
