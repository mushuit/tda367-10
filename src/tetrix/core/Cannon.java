package tetrix.core;

import org.newdawn.slick.Image;

public class Cannon extends Image {
	private float xPos;
	private float yPos;
<<<<<<< HEAD
	private Image cannonImage;
	private Track track;
	private int value;
	private int rotation;
	
=======
	private Image cannon;

>>>>>>> 68b54fd5b3e8dc1eb10c023284e1796f6f95edee
	public Cannon(float xPos, float yPos, Image cannonImage) {
		super(cannonImage);
		this.xPos = xPos;
		this.yPos = yPos;
		rotation = 0;
		value = 200;
	}
	
	public void setImage(Image cannonImage) {
		this.cannon = cannonImage;
	}
	
	public void rotate(int angle) {
		cannon.rotate(90);
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
