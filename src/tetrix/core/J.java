package tetrix.core;

import tetrix.util.Util;

public class J extends Tetromino{
	
	public J(int startX, BlockBox bBox){
		this(startX, (Util.WINDOW_WIDTH - Util.BOX_WIDTH)/2, bBox);
	}

	public J(int startX, int leftIn, BlockBox bBox){
		this(startX, leftIn, Util.SQUARE_SIZE, bBox);
	}
	
	public J(int startX, int leftIn, int fallspeed, BlockBox bBox){
		super(startX, leftIn,fallspeed, bBox);
	}


	public void build() {	
		Square[] s = super.getSquares();
		for(int i = 0; i < 4; i++){
			s[i] = new Square(new Position(super.getLeftIn(0)+(Util.SQUARE_SIZE*super.getStartX())+i*Util.SQUARE_SIZE, 80));
			if(i > 2)
				s[i] = new Square(new Position((super.getLeftIn(-Util.SQUARE_SIZE))+(Util.SQUARE_SIZE*super.getStartX())+i*Util.SQUARE_SIZE, 102));
		}
	}


}
