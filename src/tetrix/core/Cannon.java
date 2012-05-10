package tetrix.core;

import tetrix.util.Util;

/**
 * Class representing the cannon moving around the pit in which the tetrominoes are falling down.
 * @author Linus Karlsson
 *
 */
public class Cannon{
	private int xPos;
	private int yPos;
	private int value;
	private int rotation;
	
	public Cannon() {
		this(Util.WINDOW_WIDTH/2 - Util.CANNON_SIZE/2, 525);
	}

	public Cannon(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		rotation = 0;
		value = xPos - Util.CANNON_SIZE/2;
	}
	
	public float getX() {
		return xPos;
	}
	
	public float getY() {
		return yPos;
	}
	
	public void setPosition(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public Position getPosition() {
		return new Position(xPos, yPos);
	}
	
	public void move(float direction) {
		value += direction;
		if(direction > 0){
			if (value>=0 && value<=300) {
				rotation = 0;
				this.xPos += direction;
			}
			else if (value>300 && value<=800) {
				rotation = -90;
				this.yPos -= direction;
			}
			else if (value>800 && value<=1100) {
				rotation = 180;
				this.xPos -= direction;
			}
			else if (value>1100 && value<=1600){ 
				rotation = 90;
				this.yPos += direction;
			}

			if(value > 1600) {
				value = 0;
			}
			if(value < 0) {
				value = 1600;
			}
		}
		else{
			if (value>=0 && value<300) {
				rotation = 0;
				this.xPos += direction;
			}
			else if (value>=300 && value<800) {
				rotation = -90;
				this.yPos -= direction;
			}
			else if (value>=800 && value<1100) {
				rotation = 180;
				this.xPos -= direction;
			}
			else if (value>=1100 && value<=1600){ 
				rotation = 90;
				this.yPos += direction;
			}

			if(value >= 1600) {
				value = 0;
			}
			if(value <= 0) {
				value = 1600;
			}
		}
	}
	
	public int getRotation() {
		return rotation;
	}	
	
	public void reset() {
		setPosition(Util.WINDOW_WIDTH/2 - Util.CANNON_SIZE/2, 525);
		value = xPos - Util.CANNON_SIZE/2;
		rotation = 0;
	}
	
	public int getValue(){
		return (int) value;
	}
}
