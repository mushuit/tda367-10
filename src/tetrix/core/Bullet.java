package tetrix.core;

/**
 * 
 * @author Magnus Huttu
 *
 */


import org.newdawn.slick.Image;

import tetrix.util.Util;

/**
 * Class representing a bullet which is fired by the cannon on user input.
 * @author Magnus Huttu
 *
 */
public class Bullet extends Image {
	private Position pos;
	private int position = 0;
	private boolean going = false;
	private int value;
	private boolean stop;

	public Bullet(int xPos, int yPos){
		pos = new Position(0,0);
		pos.setX(xPos);
		pos.setY(yPos);
		shoot();
		stop = false;
	}

	public Bullet(Position pos, int value){
		this.value = value;
		this.pos = new Position(pos.getX() ,pos.getY());
		shoot();
		setRotation(-90);
		stop = false;
	}

	public Bullet(Position pos, Image img, float rotation, int value){
		super(img);
		rotate(rotation);
		this.value = value;
		this.pos = new Position(pos.getX() ,pos.getY());
		shoot();
		setRotation(-90);
		stop = false;
	}



	private void shoot(){

		System.out.println(pos.getX() + "    " + pos.getY());


		if(value >= 300 && value <= 800){
			position = 1;
			pos.setY(pos.getY()+22.5);
		}

		else if(value >= 0 && value <= 300){
			position = 2;
			pos.setX(pos.getX() + 22.5);
		}

		else if(value >= 800 && value <= 1100){
			position = 3;
			pos.setX((int) (pos.getX() + 22.5));
			pos.setY(pos.getY() + 50);
		}

		else if(value >= 1100 && value <= 1600){
			position = 4;
			pos.setY((int) (pos.getY() + 22.5));
			pos.setX(pos.getX() + 50);
		}

		going = true;
	}
	
	public void stop(){
		stop = true;
	}
	public Position getPos(){
		return pos;
	}

	public int getX(){
		return (int)pos.getX();
	}

	public int getY(){
		return (int)pos.getY();
	}

	public boolean getGoing(){
		return going;
	}
	
	public boolean isMoving(){
		return !stop;
	}

	public void update(){
		if(going){

			switch (position) {

			case 1:  pos.setX(pos.getX()-10); 
			if(pos.getX() < 0) 
				going = false;
			break;

			case 2:  pos.setY(pos.getY()-10); 
			if(pos.getY() < 0) 
				going = false;
			break;

			case 3:  pos.setY(pos.getY()+10); 
			if(pos.getY() > Util.WINDOW_HEIGHT) 
				going = false;
			break;

			case 4:  pos.setX(pos.getX()+10);
			if(pos.getX() > Util.WINDOW_WIDTH) 
				going = false;
			break;

			default: 
				break;

			}
		}
	}
	public void rotate(float i){
		this.setRotation(i);
	}
}
