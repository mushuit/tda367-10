package tetrix.core;

/**
 * 
 * @author Magnus Huttu
 *
 */

import tetrix.util.Util;

public class CollisionHandler {
	BlockBox bB;
	Tetromino mino;

	public CollisionHandler(BlockBox bB) {
		this.bB = bB;
	}

	public boolean checkCollision(Bullet bullet) {
		if (bullet.getPos().getY() < 0
				|| bullet.getPos().getY() > Util.WINDOW_HEIGHT)
			return true;
		else if (bullet.getPos().getX() < 0
				|| bullet.getPos().getX() > Util.WINDOW_WIDTH)
			return true;
		for (Tetromino t : bB.getTetroList()) {
			if (t.isMoving())
				for (Square s : t.getSquares()) {
					if (bullet.getPos().getY() >= s.getY()
							&& bullet.getPos().getY() <= s.getY() + 22) {
						if (bullet.getPos().getX() >= s.getX()
								&& bullet.getPos().getX() <= s.getX() + 22) {
							if (!s.destroyed()) {
								s.destroy();
								return true;
							}
						}
					}
				}
		}
		return false;
	}
}