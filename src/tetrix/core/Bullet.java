package tetrix.core;



public class Bullet {
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

	public Bullet(Position pos){
		this.pos = new Position(pos.getX() ,pos.getY());
		shoot();
	}

	private void shoot(){
	
		System.out.println(pos.getX() + "    " + pos.getY());
		
		
		if(pos.getX() == 0 && pos.getY() >= 0 && pos.getY() <= 600){
			position = 1;
		}
		else if(pos.getX() >= 500 && pos.getY() >= 0 && pos.getY() == 600){
			position = 2;
		}
		else if(pos.getY() == 0 && pos.getX() >= 0 && pos.getX() <= 500){
			position = 3;
		}else if(pos.getY() >= 550 && pos.getX() >= 0 && pos.getX() <= 500){
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
			
			case 1:  pos.setX(pos.getX()+1); 
			if(pos.getX() == 500) 
				going = false;
			break;
			
			case 2:  pos.setX(pos.getX()-1); 
			if(pos.getX() == 0) 
				going = false;
			break;
			
			case 3:  pos.setY(pos.getY()+1); 
			if(pos.getY() == 600) 
				going = false;
			break;
			
			case 4:  pos.setY(pos.getY()-1); 
			if(pos.getY() == 0) 
				going = false;
			break;
			
			default: 
			break;

			}
		}
	}
}
