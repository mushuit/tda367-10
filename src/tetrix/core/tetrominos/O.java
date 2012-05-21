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

public class O extends Tetromino {
	private boolean SqrDstr;
	private boolean SqrDstr2;
	private boolean SqrDstr3;
	private boolean SqrDstr4;
	private boolean used;

	public O(int startX, BlockBox bBox) {
		this(startX, (Util.WINDOW_WIDTH - Util.BOX_WIDTH) / 2, bBox);
	}

	public O(int startX, int leftIn, BlockBox bBox) {
		this(startX, leftIn, Util.SQUARE_SIZE, bBox);
	}

	public O(int startX, int leftIn, int fallspeed, BlockBox bBox) {
		super(startX, leftIn, fallspeed, bBox);
		SqrDstr = false;
		SqrDstr2 = false;
		SqrDstr3 = false;
		SqrDstr4 = false;
		used = false;
	}

	public void build() {
		Square[] s = super.getSquares();
		
		int startY = Util.B4_BOX_HEIGHT;
		
		if(bBox.isKonami()){
			startY = Util.B4_BOX_HEIGHT+Util.BOX_HEIGHT-Util.SQUARE_SIZE*2;
		}
		
		for (int i = 0; i < 4; i++) {
			s[i] = new Square(new Position(super.getLeftIn(0)
					+ (Util.SQUARE_SIZE * super.getStartX()) + i
					* Util.SQUARE_SIZE, startY), this, i);
			if (i > 1)
				s[i] = new Square(new Position(
						(super.getLeftIn(-Util.SQUARE_SIZE * 2))
								+ (Util.SQUARE_SIZE * super.getStartX()) + i
								* Util.SQUARE_SIZE, startY+Util.SQUARE_SIZE), this, i);
		}
	}

	@Override
	public String toString() {
		return "O";
	}

	public void notWhole() throws SlickException {
		Square[] sq2 = getSquares();
		for (Square s : getSquares()) {
			if (s.destroyed()) {
				if (!s.used()) {
					if (s.getNbr() == 0) {
						if (SqrDstr4 && !used && !SqrDstr2 && !SqrDstr3) {
							sq2[1].destroy();
							sq2[1].use();
							bBox.newBrokenBlock(1, this, sq2[1].getPos(),
									getX());
							used = true;
						}
						SqrDstr = true;
					} else if (s.getNbr() == 1) {
						if (SqrDstr3 && !used && !SqrDstr4 && !SqrDstr) {
							sq2[0].destroy();
							sq2[0].use();
							bBox.newBrokenBlock(0, this, sq2[0].getPos(),
									getX());
							used = true;
						}
						SqrDstr2 = true;
					} else if (s.getNbr() == 2) {
						if (SqrDstr2 && !used && !SqrDstr4 && !SqrDstr) {
							sq2[3].destroy();
							sq2[3].use();
							bBox.newBrokenBlock(3, this, sq2[3].getPos(),
									getX());
							used = true;
						}
						SqrDstr3 = true;
					} else if (s.getNbr() == 3) {
						if (SqrDstr && !used && !SqrDstr3 && !SqrDstr2) {
							sq2[2].destroy();
							sq2[2].use();
							bBox.newBrokenBlock(2, this, sq2[2].getPos(),
									getX());
							used = true;
						}
						SqrDstr4 = true;
					}
				}
			}
		}
	}
}
