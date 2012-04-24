package tetrix.core;

import tetrix.util.Util;

public class L extends Tetromino{

	public L(int startX){
		this(startX, (Util.WINDOW_WIDTH - Util.BOX_WIDTH)/2);
	}

	public L(int startX, int leftIn){
		this(startX, leftIn, Util.SQUARE_SIZE);
	}
	
	public L(int startX, int leftIn, int fallspeed){
		super(startX, leftIn, fallspeed);
	}

	public void build() {	
		Square[] s = super.getSquares();
		for(int i = 0; i < 4; i++){
			s[i] = new Square(new Position(super.getLeftIn(0)+(Util.SQUARE_SIZE*super.getStartX())+i*Util.SQUARE_SIZE, 80));
			if(i > 2)
				s[i] = new Square(new Position((super.getLeftIn(-3*Util.SQUARE_SIZE))+(Util.SQUARE_SIZE
						*super.getStartX())+i*Util.SQUARE_SIZE, 102));
		}
	}

	public boolean isPainted(float y, float x){
		Square[] s = super.getSquares();
		if(s[1].getY() == y + Util.SQUARE_SIZE && s[1].getX() == x)
			return true;
		else if(s[2].getY() == y + Util.SQUARE_SIZE && s[2].getX() == x)
			return true;
		else if(s[3].getY() == y + Util.SQUARE_SIZE && s[3].getX() == x)
			return true;

		return false;
	}
}
