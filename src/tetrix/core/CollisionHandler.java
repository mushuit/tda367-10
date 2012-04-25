package tetrix.core;

import java.util.ArrayList;
import java.util.List;

public class CollisionHandler {
	BlockBox bB;
	Tetromino mino;
	Square s;

	public CollisionHandler(BlockBox bB) {
		this.bB = bB;
	}

	public boolean checkCollision(Bullet bullet) {
		for(Tetromino t : bB.getTetroList()) {
			for(Square s : t.getSquares()){
				if(bullet.getPos().getY() >= s.getY() && bullet.getPos().getY() <= s.getY()+22){
					if(bullet.getPos().getX() >= s.getX() && bullet.getPos().getX() <= s.getX()+22){
						this.s = s;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public Square getSquare(){
		return s;
	}
}