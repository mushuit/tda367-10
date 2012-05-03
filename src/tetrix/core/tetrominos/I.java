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
		Square[] sq = super.getSquares();
		Square[] sq2 = super.getSquares();
		for(Square s : super.getSquares()){
			if(s.destroyed()){
				if(s.getNbr() == 1){
					sq2[0].destroy();
					super.bBox.newBlock(4);
					sq = bBox.getTetroList().get(bBox.getTetroList().size()-1).getSquares();
					sq[0].setY(sq2[2].getY());
					sq[0].setX(sq2[0].getX());
					sq[1].destroy();
					sq[2].destroy();
					sq[3].destroy();
				}
			}
		}
	}
}
