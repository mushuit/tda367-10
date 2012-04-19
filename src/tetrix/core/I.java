package tetrix.core;

public class I extends Tetromino{
	private int startX;

	public I(int startX){
		this(startX, 20);
	}
	public I(int startX, int fallspeed){
		super(startX, fallspeed);
		this.startX = startX;
	}

	public void build() {	
		Square[] s = super.getSquares();
		for(int i = 0; i < 4; i++){
			s[i] = new Square(new Position(150+(20*startX)+i*20, 100));
		}
	}

	public boolean isPainted(float y, float x){
		Square[] s = super.getSquares();
		if(s[0].getY() == y+20 && s[0].getX() == x)
			return true;
		else if(s[1].getY() == y+20 && s[1].getX() == x)
			return true;
		else if(s[2].getY() == y+20 && s[2].getX() == x)
			return true;
		else if(s[3].getY() == y+20 && s[3].getX() == x)
			return true;

		return false;
	}
}
