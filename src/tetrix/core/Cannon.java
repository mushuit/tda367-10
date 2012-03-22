package tetrix.core;

import org.newdawn.slick.Image;

public class Cannon extends Image {
	private float xPos;
	private float yPos;
	private Image cannon;

	public Cannon(float xPos, float yPos, Image cannonImage) {
		super(cannonImage);
		this.xPos = xPos;
		this.yPos = yPos;
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
	}
	
}
