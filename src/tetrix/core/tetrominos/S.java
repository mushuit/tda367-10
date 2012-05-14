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

public class S extends Tetromino{

	public S(int startX, BlockBox bBox){
		this(startX, (Util.WINDOW_WIDTH - Util.BOX_WIDTH)/2, bBox);
	}

	public S(int startX, int leftIn, BlockBox bBox){
		this(startX, leftIn, Util.SQUARE_SIZE, bBox);
	}
	
	public S(int startX, int leftIn, int fallspeed, BlockBox bBox){
		super(startX, leftIn,fallspeed, bBox);
	}


	public void build() {	
		Square[] s = super.getSquares();
		for(int i = 0; i < 4; i++){
			s[i] = new Square(new Position(super.getLeftIn(Util.SQUARE_SIZE)+(Util.SQUARE_SIZE*super.getStartX())+i*Util.SQUARE_SIZE, 80), this, i);
			if(i > 1)
				s[i] = new Square(new Position(super.getLeftIn(-2*Util.SQUARE_SIZE)+(Util.SQUARE_SIZE*super.getStartX())+i*Util.SQUARE_SIZE, 102), this, i);
		}
	}

 

	@Override
	public String toString() {
		return "S";
	}

	public void notWhole() throws SlickException{
		Square[] sq2 = getSquares();
		for(Square s : getSquares()){
			if(s.destroyed()){
				if(!s.used()){
					if(s.getNbr() == 0){
						if(!used && !SqrDstr2){
							sq2[1].destroy();
							sq2[1].use();
							bBox.newBrokenBlock(1, this, sq2[1].getPos(), getX());
							used = true;
						}
						SqrDstr = true;
					}
					else if(s.getNbr() == 1){
						SqrDstr2 = true;
					}
					else if(s.getNbr() == 2){
						SqrDstr3 = true;
					}
					else if(s.getNbr() == 3){
						if(!used && !SqrDstr3){
							sq2[2].destroy();
							sq2[2].use();
							bBox.newBrokenBlock(2, this, sq2[2].getPos(), getX());
							used = true;
						} 
						SqrDstr4 = true;
					}
				}
			}
		}
	}
}
