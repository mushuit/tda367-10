package tetrix.core;

import tetrix.core.tetrominos.I;
import tetrix.core.tetrominos.J;
import tetrix.core.tetrominos.L;
import tetrix.core.tetrominos.O;
import tetrix.core.tetrominos.S;
import tetrix.core.tetrominos.Square;
import tetrix.core.tetrominos.T;
import tetrix.core.tetrominos.Tetromino;
import tetrix.core.tetrominos.Z;

public class TetrominoFactory {
	Tetromino t;

	public void createRandomTetromino(BlockBox bB) {		
		createTetromino(bB, (int) (Math.random()*6 + 0.5));
		
	}
	
	public void createTetromino(BlockBox bB, int whichBlock) {		
		System.out.println(whichBlock);
		switch(whichBlock) {
		case 0:
			bB.addMino(new I((int)(Math.random()*7), bB));
			break;
//		case 1:
//			bB.addMino(new J((int)(Math.random()*8), bB));
//		case 2:
//			return new L((int)(Math.random()*8), bB);
//		case 3:
//			return new O((int)(Math.random()*9), bB);
//		case 4:
//			return new S((int)(Math.random()*8), bB);
//		case 5:
//			return new T((int)(Math.random()*8), bB);
//		case 6:
//			return new Z((int)(Math.random()*8), bB);
		}

	}



	public Tetromino createBrokenTetromino(BlockBox bB, int whichBlock, int sqrDestroyed, Position pos) {	
		System.out.println("Creating broken tetromino" + sqrDestroyed + "    x: " + pos.getX() + "    y: " + pos.getY());
		switch(whichBlock) {
		case 0:
				t = new I((int)(Math.random()*7), bB);
				Square[] s = t.getSquares();
			if(sqrDestroyed == 1){
				s[0].setY(pos.getY());
				s[0].setX(pos.getX());
				s[1].destroy();
				s[1].use();
				s[2].destroy();
				s[2].use();
				s[3].destroy();
				s[3].use();
			}
			if(sqrDestroyed == 2){
				s[3].setY(pos.getY());
				s[3].setX(pos.getX());
				s[0].destroy();
				s[0].use();
				s[1].destroy();
				s[1].use();
				s[2].destroy();
				s[2].use();
			}
			
			bB.addMino(t);

		case 1:
			return new J((int)(Math.random()*8), bB);
		case 2:
			return new L((int)(Math.random()*8), bB);
		case 3:
			return new O((int)(Math.random()*9), bB);
		case 4:
			return new S((int)(Math.random()*8), bB);
		case 5:
			return new T((int)(Math.random()*8), bB);
		case 6:
			return new Z((int)(Math.random()*8), bB);
		}

		return null;
	}
}

