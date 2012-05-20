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

/**
 * Creates the different tetrominos
 * 
 * @author Magnus Huttu
 *
 */

public class TetrominoFactory {
	Tetromino t;

	public TetrominoFactory() {
	}

	public void createRandomTetromino(BlockBox bB) {
		createTetromino(bB, (int) (Math.random() * 6));

	}

	public void createTetromino(BlockBox bB, int whichBlock) {
		System.out.println(whichBlock);
		switch (whichBlock) {
		case 0:
			bB.addMino(new I((int) (Math.random() * 7), bB));
			break;
		case 1:
			bB.addMino(new J((int) (Math.random() * 8), bB));
			break;
		case 2:
			bB.addMino(new L((int) (Math.random() * 8), bB));
			break;
		case 3:
			bB.addMino(new O((int) (Math.random() * 9), bB));
			break;
		case 4:
			bB.addMino(new S((int) (Math.random() * 8), bB));
			break;
		case 5:
			bB.addMino(new T((int) (Math.random() * 8), bB));
			break;
		case 6:
			bB.addMino(new Z((int) (Math.random() * 8), bB));
			break;
		}

	}

	public void createBrokenTetromino(BlockBox bB, Tetromino te, int newSqr,
			Position p, int x) {
		Position pos = new Position(p);
		t = te;
		if (t.toString().equals("I")) {
			t = new I(x, bB);
		} else if (t.toString().equals("J")) {
			t = new J(x, bB);
		} else if (t.toString().equals("L")) {
			t = new L(x, bB);
		} else if (t.toString().equals("O")) {
			t = new O(x, bB);
		} else if (t.toString().equals("T")) {
			t = new T(x, bB);
		} else if (t.toString().equals("S")) {
			t = new S(x, bB);
		} else if (t.toString().equals("Z")) {
			t = new Z(x, bB);
		}

		Square[] s = t.getSquares();
		s[0].destroy();
		s[0].use();
		s[1].destroy();
		s[1].use();
		s[2].destroy();
		s[2].use();
		s[3].destroy();
		s[3].use();

		s[newSqr].setY(pos.getY());
		s[newSqr].setX(pos.getX());
		if ((newSqr == 0 || newSqr == 1) && t.toString().equals("O")) {
			s[newSqr].setY(pos.getY() + Util.SQUARE_SIZE);
		} else if (newSqr == 0 && t.toString().equals("J")) {
			s[newSqr].setY(pos.getY() + Util.SQUARE_SIZE);
		} else if (newSqr == 2 && t.toString().equals("L")) {
			s[newSqr].setY(pos.getY() + Util.SQUARE_SIZE);
		} else if (t.toString().equals("S")) {
			s[newSqr].setY(pos.getY() + Util.SQUARE_SIZE);
		} else if (newSqr == 2 && t.toString().equals("T")) {
			s[newSqr].setY(pos.getY() + Util.SQUARE_SIZE);
		} else if (newSqr == 0 && t.toString().equals("Z")) {
			s[newSqr].setY(pos.getY() + Util.SQUARE_SIZE);
		} else if (newSqr == 0 && t.toString().equals("I")) {
			s[newSqr].setY(pos.getY() + Util.SQUARE_SIZE);
		}

		s[newSqr].unDestroy();
		s[newSqr].unUse();

		bB.addMino(t);
	}
}
