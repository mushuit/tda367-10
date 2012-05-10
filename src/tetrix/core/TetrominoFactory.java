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
import tetrix.util.Util;

public class TetrominoFactory {
	Tetromino t;
	Tetromino I;
	Tetromino J;
	Tetromino L;
	Tetromino O;
	Tetromino T;
	Tetromino S;
	Tetromino Z;



	public TetrominoFactory(){
		Tetromino I = new I(0, null);
		Tetromino J = new J(0, null);
		Tetromino L = new L(0, null);
		Tetromino O = new O(0, null);
		Tetromino T = new T(0, null);
		Tetromino S = new S(0, null);
		Tetromino Z = new Z(0, null);
	}

	public void createRandomTetromino(BlockBox bB) {		
		createTetromino(bB, (int) (Math.random()*6));

	}

	public void createTetromino(BlockBox bB, int whichBlock) {		
		System.out.println(whichBlock);
		switch(whichBlock) {
		case 0:
			bB.addMino(new I((int)(Math.random()*7), bB));
			break;
		case 1:
			bB.addMino(new J((int)(Math.random()*8), bB));
			break;
		case 2:
			bB.addMino(new L((int)(Math.random()*8), bB));
			break;
		case 3:
			bB.addMino(new O((int)(Math.random()*9), bB));
			break;
		case 4:
			bB.addMino(new S((int)(Math.random()*8), bB));
			break;
		case 5:
			bB.addMino(new T((int)(Math.random()*8), bB));
			break;
		case 6:
			bB.addMino(new Z((int)(Math.random()*8), bB));
			break;
		}

	}



	public void createBrokenTetromino(BlockBox bB, int sqrDestroyed, Position pos, int x) {	
		System.out.println("Creating broken tetromino" + sqrDestroyed + "    x: " + pos.getX() + "    y: " + pos.getY());


		if(true){
			t = new I(x, bB);
			Square[] s = t.getSquares();
			s[1].destroy();
			s[1].use();
			s[2].destroy();
			s[2].use();
			if(sqrDestroyed == 1){
				s[0].setY(pos.getY());
				s[3].destroy();
				s[3].use();
			} else if(sqrDestroyed == 2){
				s[3].setY(pos.getY()+Util.SQUARE_SIZE);
				s[0].destroy();
				s[0].use();
			}
		}

		bB.addMino(t);
	}
}

