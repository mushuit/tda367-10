package tetrix.core;

import javax.swing.text.Position;

import org.newdawn.slick.Image;

public class Cannon {
	private float xPos;
	private float yPos;
	private Image cannonImage;
	
	public Cannon(float xPos, float yPos, Image cannonImage) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.cannonImage = cannonImage;
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
	
	public void rotate(int angle) {
		cannonImage.rotate(angle);
	}
	
}
