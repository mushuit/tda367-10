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

public class Z extends Tetromino{

	public Z(int startX, BlockBox bBox){
		this(startX, (Util.WINDOW_WIDTH - Util.BOX_WIDTH)/2, bBox);
	}

	public Z(int startX, int leftIn, BlockBox bBox){
		this(startX, leftIn, Util.SQUARE_SIZE, bBox);
	}
	
	public Z(int startX, int leftIn, int fallspeed, BlockBox bBox){
		super(startX, leftIn,fallspeed, bBox);
	}


	public void build() {	
		Square[] s = super.getSquares();
		for(int i = 0; i < 4; i++){
			s[i] = new Square(new Position(super.getLeftIn(0)+(
					Util.SQUARE_SIZE*super.getStartX())+i*Util.SQUARE_SIZE, 80), this, i);
			if(i > 1)
				s[i] = new Square(new Position(super.getLeftIn(
						-Util.SQUARE_SIZE)+(Util.SQUARE_SIZE*super.getStartX())+i*Util.SQUARE_SIZE, 102), this, i);
		}
	}


	@Override
	public String toString() {
		return "Z";
	}
	
	public void notWhole() throws SlickException{
		Square[] sq2 = getSquares();
		for(Square s : getSquares()){
			if(s.destroyed()){
				if(!s.used()){
					if(s.getNbr() == 0){
						SqrDstr = true;
					}
					else if(s.getNbr() == 1){
						if(!used && !SqrDstr){
							sq2[0].destroy();
							sq2[0].use();
							bBox.newBrokenBlock(0, this, sq2[0].getPos(), getX());
							used = true;
						}
						SqrDstr2 = true;
					}
					else if(s.getNbr() == 2){
						if(!used && !SqrDstr4){
							sq2[3].destroy();
							sq2[3].use();
							bBox.newBrokenBlock(3, this, sq2[3].getPos(), getX());
							used = true;
						} 
						SqrDstr3 = true;
					}
					else if(s.getNbr() == 3){
						SqrDstr4 = true;
					}
				}
			}
		}
	}
}
