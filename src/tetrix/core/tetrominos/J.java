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

public class J extends Tetromino{
	private boolean SqrDstr;
	private boolean SqrDstr2;
	private boolean SqrDstr3;
	private boolean SqrDstr4;
	private boolean used;

	public J(int startX, BlockBox bBox){
		this(startX, (Util.WINDOW_WIDTH - Util.BOX_WIDTH)/2, bBox);
	}

	public J(int startX, int leftIn, BlockBox bBox){
		this(startX, leftIn, Util.SQUARE_SIZE, bBox);
	}

	public J(int startX, int leftIn, int fallspeed, BlockBox bBox){
		super(startX, leftIn,fallspeed, bBox);
		SqrDstr = false;
		SqrDstr2 = false;
		SqrDstr3 = false;
		SqrDstr4 = false; 
		used = false;
	}


	public void build() {	
		Square[] s = super.getSquares();
		for(int i = 0; i < 4; i++){
			s[i] = new Square(new Position(super.getLeftIn(0)+(Util.SQUARE_SIZE*super.getStartX())+i*Util.SQUARE_SIZE, 80), this, i);
			if(i > 2)
				s[i] = new Square(new Position((super.getLeftIn(-Util.SQUARE_SIZE))+(Util.SQUARE_SIZE*super.getStartX())+i*Util.SQUARE_SIZE, 102), this, i);
		}
	}


	@Override
	public String toString() {
		return "J";
	}

	public void notWhole() throws SlickException{
		System.out.println("1:" + SqrDstr + "  2:" + SqrDstr2 + "  3:" + SqrDstr3 + "  4:" + SqrDstr4);
		Square[] sq2 = getSquares();
		for(Square s : getSquares()){
			if(s.destroyed()){
				if(!s.used()){
					if(s.getNbr() == 0){

						SqrDstr = true;
					}
					else if(s.getNbr() == 1){
						if(SqrDstr3 && !used && !SqrDstr4 && !SqrDstr){
							sq2[0].destroy();
							sq2[0].use();
							bBox.newBrokenBlock(0, this, sq2[0].getPos(), getX());
							System.out.println("clonad()");
							used = true;
						} 
						SqrDstr2 = true;
					}
					else if(s.getNbr() == 2){
						if(SqrDstr2 && !used && !SqrDstr4 && !SqrDstr){
							sq2[3].destroy();
							sq2[3].use();
							bBox.newBrokenBlock(3, this, sq2[3].getPos(), getX());
							System.out.println("clonad()");
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
		for(Square s : getSquares()){
			if(s.destroyed()){
				if(!s.used()){
					if(s.getNbr() == 1){
						sq2[0].destroy();
						sq2[0].use();
						bBox.newBrokenBlock(0, this, sq2[0].getPos(), getX());
					}
					else if(s.getNbr() == 2){
						sq2[3].destroy();
						sq2[3].destroy();
						bBox.newBrokenBlock(3, this, sq2[3].getPos(), getX());
					}
				}
			}
		}
	}

}
