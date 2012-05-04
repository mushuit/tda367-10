package tetrix.core.tetrominos;

/**
 * 
 * @author Magnus Huttu
 *
 */

import java.awt.event.ActionEvent;

import tetrix.core.BlockBox;
import tetrix.core.Position;
import tetrix.util.Util;

public class O extends Tetromino{

	public O(int startX, BlockBox bBox){
		this(startX, (Util.WINDOW_WIDTH - Util.BOX_WIDTH)/2, bBox);
	}

	public O(int startX, int leftIn, BlockBox bBox){
		this(startX, leftIn, Util.SQUARE_SIZE, bBox);
	}
	
	public O(int startX, int leftIn, int fallspeed, BlockBox bBox){
		super(startX, leftIn,fallspeed, bBox);
	}


	public void build() {	
		Square[] s = super.getSquares();
		for(int i = 0; i < 4; i++){
			s[i] = new Square(new Position(super.getLeftIn(0)+(Util.SQUARE_SIZE*super.getStartX())+i*Util.SQUARE_SIZE, 80));
			if(i > 1)
				s[i] = new Square(new Position((super.getLeftIn(-Util.SQUARE_SIZE*2))+(Util.SQUARE_SIZE*super.getStartX())+i*Util.SQUARE_SIZE, 102));
		}
	}

	@Override
	public void notWhole() {
		// TODO Auto-generated method stub
		
	}



}