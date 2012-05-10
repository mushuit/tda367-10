package tetrix.core.tetrominos;

/**
 * 
 * @author Magnus Huttu
 *
 */

import java.awt.event.ActionEvent;

import org.newdawn.slick.SlickException;

import tetrix.core.BlockBox;
import tetrix.core.Position;
import tetrix.util.Util;

public class O extends Tetromino{
	private boolean bothSqrDstr;
	private boolean bothSqrDstr2;

	public O(int startX, BlockBox bBox){
		this(startX, (Util.WINDOW_WIDTH - Util.BOX_WIDTH)/2, bBox);
	}

	public O(int startX, int leftIn, BlockBox bBox){
		this(startX, leftIn, Util.SQUARE_SIZE, bBox);
	}

	public O(int startX, int leftIn, int fallspeed, BlockBox bBox){
		super(startX, leftIn,fallspeed, bBox);
		bothSqrDstr = false;
		bothSqrDstr2 = false; 
	}

	public void build() {	
		Square[] s = super.getSquares();
		for(int i = 0; i < 4; i++){
			s[i] = new Square(new Position(super.getLeftIn(0)+(Util.SQUARE_SIZE*super.getStartX())+i*Util.SQUARE_SIZE, 80), this, i);
			if(i > 1)
				s[i] = new Square(new Position((super.getLeftIn(-Util.SQUARE_SIZE*2))+(Util.SQUARE_SIZE*super.getStartX())+i*Util.SQUARE_SIZE, 102), this, i);
		}
	}

	@Override
	public String toString() {
		return "O";
	}

	public void notWhole() throws SlickException{
		System.out.println("1:" + bothSqrDstr + "  2:" +bothSqrDstr2);
		Square[] sq2 = getSquares();
		for(Square s : getSquares()){
			if(s.destroyed()){
				if(s.getNbr() == 0){
					if(bothSqrDstr2){
						sq2[1].destroy();
						bBox.newBrokenBlock(1, this, sq2[1].getPos(), getX());
						System.out.println("clonad()");
					} 
					bothSqrDstr2 = true;

				}
				else if(s.getNbr() == 1){
					if(bothSqrDstr){
						sq2[0].destroy();
						bBox.newBrokenBlock(0, this, sq2[0].getPos(), getX());
						System.out.println("clonad()");
					} 
					bothSqrDstr = true;


				}
				else if(s.getNbr() == 2){
					if(bothSqrDstr){
						sq2[3].destroy();
						bBox.newBrokenBlock(3, this, sq2[3].getPos(), getX());
						System.out.println("clonad()");
					}
					bothSqrDstr = true;

				}
				else if(s.getNbr() == 3){
					if(bothSqrDstr2){
						sq2[2].destroy();
						bBox.newBrokenBlock(2, this, sq2[2].getPos(), getX());
						System.out.println("clonad()");
					} 
					bothSqrDstr2 = true;

				}
			}
		}
	}
}
