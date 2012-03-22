package tetrix.core;

import org.newdawn.slick.Image;



public class Bullet extends Image {
	private Position pos;
	private int position = 0;
	private boolean going = false;
	//xmax = 500; ymax = 600;

	public Bullet(float xPos, float yPos){
		pos = new Position(0,0);
		pos.setX(xPos);
		pos.setY(yPos);
		shoot();
	}

	public Bullet(Position pos, Image img, float rotation){
		super(img);
		rotate(rotation);
		this.pos = new Position(pos.getX() ,pos.getY());
		shoot();
		setRotation(-90);
	}

	private void shoot(){
	
		System.out.println(pos.getX() + "    " + pos.getY());
		
		
		if(pos.getX() <= 50 && pos.getY() >= 50 && pos.getY() <= 550){
			position = 1;
		}
		
		else if(pos.getX() >= 450 && pos.getY() >= 50 && pos.getY() == 550){
			position = 2;
		}
		
		else if(pos.getY() <= 50 && pos.getX() >= 50 && pos.getX() <= 450){
			position = 3;
		}
		
		else if(pos.getY() >= 550 && pos.getX() >= 50 && pos.getX() <= 450){
			position = 4;
		}

		going = true;
	}

	public Position getPos(){
		return pos;
	}
	
	public boolean getGoing(){
		return going;
	}

	public void update(){
		if(going){
			
			switch (position) {
			
			case 1:  pos.setX(pos.getX()+55); 
			if(pos.getX() == 800) 
				going = false;
			break;
			
			case 2:  pos.setX(pos.getX()-55); 
			if(pos.getX() == -200) 
				going = false;
			break;
			
			case 3:  pos.setY(pos.getY()+50); 
			if(pos.getY() == 800) 
				going = false;
			break;
			
			case 4:  pos.setY(pos.getY()-50);
			if(pos.getY() < -200) 
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
