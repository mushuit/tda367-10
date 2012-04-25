package tetrix.core;

import java.util.ArrayList;
import java.util.List;

public class CollisionHandler {
	BlockBox bB;
	Tetromino mino;
	
	public CollisionHandler(BlockBox bB) {
		this.bB = bB;
	}
	
	public void checkCollision(Bullet bullet, ArrayList tetromino) {
		for(Tetromino t : tetromino) {
			if(bullet.getPos() >= bB.getTetroList().get(i).getSquares().getY()) {
				
			}
		}
	}
}
