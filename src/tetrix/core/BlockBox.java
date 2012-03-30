package tetrix.core;


import java.util.ArrayList;
<<<<<<< HEAD
import java.util.List;

<<<<<<< HEAD
=======
>>>>>>> Testing and implementing high score class
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BlockBox {

	private int width;
	private int height;
	private int yPos;
	private int xPos;
	private boolean falsk;
	private Tetromino mino;
	private List minoes;

	private boolean[][] blockBox;
	private Image[][] blockImg;
	private Position[][] blockPos;
	private Image img;

	public BlockBox() throws SlickException {
		new BlockBox(10,20);
	}

	public BlockBox(int width, int height) throws SlickException{
		this.width = width;
		this.height = height;
		falsk = false;

		minoes = new ArrayList();

		img = new Image("img/block.png");
		blockPos = new Position[width][height];
		blockImg = new Image[width][height];
		blockBox = new boolean[width][height];

		yPos = 0;
		xPos = 5;
		init();
		clearBoard();
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
			blockBox[x][y] = true;
		}	
	}

	public void update() throws SlickException {
		for(int i = 0; i < blockBox.length; i++) {
			for(int j = 0; j < blockBox[i].length; j++) {
				if(blockBox[i][j]){	
					paint(i, j);
					blockPos[i][j].setY(move(blockPos[i][j].getY()));
					if(!isPainted(i,j)){
						blockBox[i][j+1] = true;
						blockBox[i][j] = false;
					}
					break;
				}
			}
		}


	}

	public void newBlock(int i){
		if(i < 10)
			blockBox[i][0]  = true;
	}

	public boolean isPainted(int x, int y) {
		if(y != 19)
			return blockBox[x][y+1]; 
		else
			return true;
	} 

	public void makeEmpty(int x, int y) {
		blockBox[x][y] = false;

	}

	private void init() throws SlickException{
		int k = 150;
		int l = 100;
		for(int j = 0; j < height; j++) {
			for(int i = 0; i < width; i++) {
				blockPos[i][j] = new Position(k,l);
				blockBox[i][j] = falsk; 
				blockImg[i][j] = new Image("img/block.png");
				System.out.println(" "+ blockPos[i][j].getX()+"  "+blockBox[i][j]+"  " + blockImg[i][j].getWidth() +" j: "+ j + " i: "+i);
				l+=20;
			}
			k+=20;
		}
	}

	public Position getPos(int x, int y){
		return blockPos[x][y];
	}

	public Image getImg(int x ,int y) {
		return blockImg[x][y];
	}

	public boolean getBox(int x, int y){
		return blockBox[x][y];
	}

	public float move(float yPos){
		return yPos+1;
	}
}
