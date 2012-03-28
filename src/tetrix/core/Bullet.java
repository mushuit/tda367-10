package tetrix.core;

import org.newdawn.slick.Image;

/**
 * Class representing a bullet which is fired by the cannon on user input.
 * @author Magnus Huttu
 *
 */
public class Bullet extends Image {
	private Position pos;
	private int position = 0;
	private boolean going = false;
	private int xMax = 500, yMax = 600;
	private int value;

	public Bullet(float xPos, float yPos){
		pos = new Position(0,0);
		pos.setX(xPos);
		pos.setY(yPos);
		shoot();
	}

	public Bullet(Position pos, Image img, float rotation, int value){
		super(img);
		rotate(rotation);
		this.value = value;
		this.pos = new Position(pos.getX() ,pos.getY());
		shoot();
		setRotation(-90);
	}

	public Bullet(Position pos, int value){
		this.value = value;
		this.pos = new Position(pos.getX() ,pos.getY());
		shoot();
		setRotation(-90);
	}


	private void shoot(){
	
		System.out.println(pos.getX() + "    " + pos.getY());
		
		
		if(value >= 400 && value <= 900){
			position = 1;
			pos.setY((float) (pos.getY()+22.5));
		}
		
		else if(value >= 0 && value <= 400){
			position = 2;
			pos.setX((float) (pos.getX() + 22.5));
		}
		
		else if(value >= 900 && value <= 1300){
			position = 3;
			pos.setX((float) (pos.getX() + 22.5));
			pos.setY(pos.getY() + 50);
		}
		
		else if(value >= 1300 && value <= 1800){
			position = 4;
			pos.setY((float) (pos.getY() + 22.5));
			pos.setX(pos.getX() + 50);
		}

		going = true;
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
			if(pos.getY() > yMax) 
				going = false;
			break;
			
			case 4:  pos.setX(pos.getX()+10);
			if(pos.getX() > xMax) 
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
