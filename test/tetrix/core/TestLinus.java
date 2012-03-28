package tetrix.core;

public class TestLinus {
	
	private int width;
	private int height;
	
	private int[][] blockBox;

	public TestLinus() {
	//	new BlockBox(10,20);
	}
	
	public TestLinus(int width, int height){
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
