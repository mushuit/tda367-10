package tetrix.core;

public class PowerBullet {
	private Position pos;
	int position = 0;

	public PowerBullet(int xPos, int yPos){
		pos = new Position(0,0);
		pos.setX(xPos);
		pos.setY(yPos);
		shoot();
	}
	
	public PowerBullet(Position pos){
		this.pos = pos;
		shoot();
	}

	private void shoot(){
		if(pos.getX() == 0 && pos.getY() > 0 && pos.getY() < 600){
			position = 1;
		}
		else if(pos.getX() == 500 && pos.getY() > 0 && pos.getY() < 600){
			position = 2;
		}
		else if(pos.getY() == 0 && pos.getX() > 0 && pos.getX() < 500){
			position = 3;
		}else{
			position = 4;
		}
	}
	
	public Position getPos(){
		return pos;
	}
	
	public void update(){ 
		switch (position) {
	case 1:  pos.setX(pos.getX()+1); break;
	case 2:  pos.setX(pos.getX()-1); break;
	case 3:  pos.setY(pos.getY()+1); break;
	case 4:  pos.setY(pos.getY()-1); break;
	default: break;
		}
	}
	
}