package tetrix.core.tetrominos;

/**
 * 
 * @author Magnus Huttu
 *
 */

import org.newdawn.slick.SlickException;

import tetrix.core.BlockBox;
import tetrix.core.Position;
import tetrix.util.Util;

public class T extends Tetromino{
	
	public T(int startX, BlockBox bBox){
		this(startX, (Util.WINDOW_WIDTH - Util.BOX_WIDTH)/2, bBox);
	}

	public T(int startX, int leftIn, BlockBox bBox){
		this(startX, leftIn, Util.SQUARE_SIZE, bBox);
	}
	
	public T(int startX, int leftIn, int fallspeed, BlockBox bBox){
		super(startX, leftIn,fallspeed, bBox);
	}

	public void build() {		
		Square[] s = super.getSquares();
		for(int i = 0; i < 4; i++){
			s[i] = new Square(new Position(super.getLeftIn(0)+(Util.SQUARE_SIZE*super.getStartX())+i*Util.SQUARE_SIZE, 80), this, i);
			if(i > 2)
				s[i] = new Square(new Position(super.getLeftIn(-Util.SQUARE_SIZE*2)+(Util.SQUARE_SIZE*super.getStartX())+i*Util.SQUARE_SIZE, 102), this, i);
		}
	}



	@Override
	public String toString() {
		return "T";
	}

	public void notWhole() throws SlickException{
		Square[] sq2 = getSquares();
		for(Square s : getSquares()){
			if(s.destroyed()){
				if(s.getNbr() == 1){
					sq2[0].destroy();
					sq2[2].destroy();
					bBox.newBrokenBlock(0, this, sq2[0].getPos(), getX());
					bBox.newBrokenBlock(2, this, sq2[2].getPos(), getX());
				}
			}
		}
	}
}
