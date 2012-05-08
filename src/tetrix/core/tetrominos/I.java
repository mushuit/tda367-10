package tetrix.core.tetrominos;

/**
 * 
 * @author Magnus Huttu
 *
 */


import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import tetrix.core.BlockBox;
import tetrix.core.Position;
import tetrix.util.Util;

public class I extends Tetromino{
	private int i = 0;

	public I(int startX, BlockBox bBox){
		this(startX, (Util.WINDOW_WIDTH - Util.BOX_WIDTH)/2, bBox);
	}

	public I(int startX, int leftIn, BlockBox bBox){
		this(startX, leftIn, Util.SQUARE_SIZE, bBox);
	}

	public I(int startX, int leftIn, int fallspeed, BlockBox bBox){
		super(startX, leftIn,fallspeed, bBox);
	}

	public void build() {	
		Square[] s = super.getSquares();
		for(int i = 0; i < 4; i++){
			s[i] = new Square(new Position(super.getLeftIn(0)+(Util.SQUARE_SIZE*super.getStartX())+i*Util.SQUARE_SIZE, 80), this, i);
		}
	}

	public void notWhole() throws SlickException{
		//	System.out.println("notWhole()");
		i++;
		System.out.println(i); 
		Square[] sq2 = super.getSquares();
		for(Square s : super.getSquares()){
			if(s.destroyed()){
				if(s.getNbr() == 1){
					sq2[0].destroy();
					super.bBox.newBlock(0, 1, sq2[0].getPos());
				}
				else if(s.getNbr() == 2){
					sq2[0].destroy();
					super.bBox.newBlock(0, 1, sq2[0].getPos());
				}
			}
		}
	}
}
