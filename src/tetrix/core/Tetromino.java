package tetrix.core;

public abstract class Tetromino {
	private Square[] square;
	private int blockForm;
	private int startX;
	private boolean[] hasSquare;
	private int fallspeed;
	private boolean stop;
	
	public Tetromino(int startX){
		this(startX, 20);
	}
	
	public Tetromino(int startX, int fallspeed){
		this.startX = startX;
		this.fallspeed = fallspeed;
		square = new Square[4];
		build();
		stop = false;
	}
	
	public Position[] getPos(){
		Position[] pos = new Position[4];
		int i = 0;
		for(Square s : square){
			pos[i] = s.getPos();
			i++;
		}

		return pos.clone();
		
	}
	
	public void build(){
		
	}
	
	public float falling(float f){
		return f + fallspeed;
		
	}
	
	public void update(){
		for(Square s : square){
			s.setY(falling(s.getY()));
		}
		
	}
	
	public Square[] getSquares(){
		return square;
	}
	
	public void stop(){
		stop = true;
	}
	
	public boolean stopped(){
		return stop;
	}
	
	public int getStartX(){
		return startX;
	}
	
	public boolean isPainted(float y, float x){
		return false;
	}
	
	
}
