package tetrix.core;

public class Position {
	float x = 0;
	float y = 0;

	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}

	public Position(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public Position(Position pos){
		this.x = pos.getX();
		this.y = pos.getY();
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
}
