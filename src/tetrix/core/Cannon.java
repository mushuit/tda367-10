package tetrix.core;

import org.newdawn.slick.Image;

public class Cannon extends Image {
	private float xPos;
	private float yPos;
	private Image cannonImage;
	private Track track;
	
	public Cannon(float xPos, float yPos, Image cannonImage) {
		super(cannonImage);
		this.xPos = xPos;
		this.yPos = yPos;
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
	
	public Position getPosition(float xPos, float yPos) {
		return new Position(xPos, yPos);
	}
	
	public void move(float x, float y) {
		this.xPos += x;
		this.yPos += y;
	}
	
}
