package tetrix.core;

import java.util.ArrayList;

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
