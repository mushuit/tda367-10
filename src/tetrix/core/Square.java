package tetrix.core;

public class Square {
	private Position pos;
	private boolean destroyed;
	
	public Square(Position pos) {
		this.pos = pos;
	}
	
	public Position getPos(){
		return new Position(pos);
	}
	
	public void setPos(Position pos){
		this.pos = pos;
	}
	
	public void setY(float f){
		pos.setY(f);
	}

	public float getY(){
		return pos.getY();
	}

	public float getX() {
		return pos.getX();
	}
	
	public boolean destroyed(){
		return destroyed;
	}
	
	public void destroy(){
		destroyed = true;
	}
}
