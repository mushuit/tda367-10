package tetrix.core;

public class Position {
	private int x = 0;
	private int y = 0;

	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Position(Position pos){
		this.x = pos.getX();
		this.y = pos.getY();
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(double x){
		this.x = (int)x;
	}
	
	public void setY(double y){
		this.y = (int)y;
	}
	
	public void setPosition(Position pos){
		this.x = pos.getX();
		this.y = pos.getY();
	}
	
	@Override
	public String toString(){
		return "X-position: " + x + "   Y-position: " + y;
		
	}
	
}
