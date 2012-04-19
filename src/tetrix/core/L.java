package tetrix.core;

public class L extends Tetromino{

	public L(int startX){
		this(startX, 20);
	}
	public L(int startX, int fallspeed){
		super(startX, fallspeed);
	}

	public void build() {	
		Square[] s = super.getSquares();
		for(int i = 0; i < 4; i++){
			s[i] = new Square(new Position(150+(20*super.getStartX())+i*20, 100));
			if(i > 2)
				s[i] = new Square(new Position(90+(20*super.getStartX())+i*20, 120));
		}
	}

	public boolean isPainted(float y, float x){
		Square[] s = super.getSquares();
		if(s[1].getY() == y+20 && s[1].getX() == x)
			return true;
		else if(s[2].getY() == y+20 && s[2].getX() == x)
			return true;
		else if(s[3].getY() == y+20 && s[3].getX() == x)
			return true;

		return false;
	}
}
