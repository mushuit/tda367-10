package tetrix.core;

<<<<<<< HEAD
import java.util.ArrayList;
=======
public class BlockBox {
	private boolean[][] fallingBlocks;
	private boolean[][] lockedBlocks;
	private Tetromino mino;
	private boolean[][] bMino;
	private Position[][] pos;

	public BlockBox(){
		fallingBlocks = new boolean[10][20];
		lockedBlocks = new boolean[10][20];
		pos = new Position[10][20];

		for(int h = 0; h < 20; h++){
			for(int i = 0; i < 10; i++){
				fallingBlocks[i][h] = false;
				lockedBlocks[i][h] = false;
			}
		}
		newBlock();
	}
>>>>>>> eaa49768b3ef89e4476ce7e9fd5c754b1665d6fe

public class BlockBox {
	
	private int width;
	private int height;
	
	private int[][] blockBox;

	public BlockBox() {
		new BlockBox(10,20);
	}
	
	public BlockBox(int width, int height){
		this.width = width;
		this.height = height;
		
		blockBox = new int[width][height];
		clearBoard();
	}
	
	public void clearBoard() {
		for(int i = 0; i < blockBox.length; i++) {
			for(int j = 0; j < blockBox[i].length; j++) {
				//blockBox[i][j] = Tetromino.EMPTY;
			}
		}
	}
	
	
	
	
		
	
}
