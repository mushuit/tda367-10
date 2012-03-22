package tetrix.core;

import org.newdawn.slick.Image;

public class Cannon extends Image {
	private float xPos;
	private float yPos;
	private Image cannonImage;
	private Track track;
	private int value;
	private int rotation;
	
	public Cannon(float xPos, float yPos, Image cannonImage) {
		super(cannonImage);
		this.xPos = xPos;
		this.yPos = yPos;
		rotation = 0;
		value = 200;
	}
	
	public void setImage(Image cannonImage) {
		this.cannonImage = cannonImage;
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
		this.xPos += x;
		this.yPos += y;
		value += x+(-y);
		if (x>=0 && x<=400) {
			rotation = 0;
		}
		else if (x>400 && x<=900) {
			rotation = 90;
		}
		else if (x>900 && x<=1400) {
			rotation = 180;
		}
		else { 
			rotation = 240;
		
		}
	}
	
	public float getRotation() {
		return rotation;
	}
	
	
}
