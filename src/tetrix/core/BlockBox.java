package tetrix.core;

<<<<<<< HEAD
<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> 2a24f4f6cc5ac0eca0617e95684bb28e8020077d
=======
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

>>>>>>> Försöker få blocken att fungera
public class BlockBox {

	private int width;
	private int height;
	private int yPos;
	private int xPos;

	private boolean[][] blockBox;
	private Image[][] blockImg;
	private Position[][] blockPos;

	public BlockBox() {
		new BlockBox(10,20);
	}

	public BlockBox(int width, int height){
		this.width = width;
		this.height = height;

		blockBox = new boolean[width][height];
		clearBoard();
		initPositions();
	}

	public void clearBoard() {
		for(int i = 0; i < blockBox.length; i++) {
			for(int j = 0; j < blockBox[i].length; j++) {
				blockBox[i][j] = false;
			}
		}
	}

	public void paint(int x, int y) {
		if(!isPainted(x,y)){
			xPos = x;
			yPos = y;
			blockBox[xPos][yPos] = true;
		}	
	}

	public void update() throws SlickException {
		makeEmpty(yPos, xPos);
		yPos++;
		paint(xPos, yPos);

		for(int i = 0; i < blockBox.length; i++) {
			for(int j = 0; j < blockBox[i].length; j++) {
				if(blockBox[i][j])
					blockImg[i][j].startUse();
				else
					blockImg[i][j].destroy();
			}
		}

	}

	public boolean isPainted(int x, int y) {
		return blockBox[x][y]; 
	} 

	public void makeEmpty(int x, int y) {
		blockBox[x][y] = false;

	}

	private void initPositions(){
		for(int i = 150; i < 350; i+=20) {
			for(int j = 100; j < 500; j+=20) {
				blockPos[i][j].setX(i);
				blockPos[i][j].setY(j);
			}
		}
	}

	public Position getPos(int x, int y){
		return blockPos[x][y];
	}
}
