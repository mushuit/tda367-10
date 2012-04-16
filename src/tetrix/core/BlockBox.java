package tetrix.core;


import java.util.ArrayList;
import java.util.List;

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
	private List<Tetromino> minoes;
	private List<Tetromino> fMinoes;

	private boolean[][] blockBox;
	private Image[][] blockImg;
	private Position[][] blockPos;
	private Image img;

	public BlockBox() throws SlickException {
		this(10,20);
	}

	public BlockBox(int width, int height) throws SlickException{
		this.width = width;
		this.height = height;
		falsk = false;

		minoes = new ArrayList();
		fMinoes = new ArrayList();

		img = new Image("img/block.png");
		blockPos = new Position[width][height];
		blockImg = new Image[width][height];
		blockBox = new boolean[width][height];

		clearBoard();
	}

	public void clearBoard() {
		minoes.clear();
		fMinoes.clear();
	}

	public void update(){
		for(Tetromino t : minoes){
			t.update();
		}
	}
	/*public void update() throws SlickException {
		for(int i = 0; i < blockBox.length; i++) {
			for(int j = 0; j < blockBox[i].length; j++) {
				if(blockBox[i][j]){	
					paint(i, j);
					blockPos[i][j].setY((blockPos[i][j].getY()));
					if(!isPainted(i,j)){
						blockBox[i][j+1] = true;
						blockBox[i][j] = false;
					}
					break;
				}
			}
		}*/


	//}

	public void newBlock() throws SlickException{
		minoes.add(new Tetromino());
	}

	public boolean isPainted(int x, int y) {
		for(int i = 0; i < fMinoes.size(); i++){
			for(Position p : fMinoes.get(i).getPos()){
				if(p.getX() == x){
					if(p.getY() == y+1){
						return true;
					}
				}
			}
		}
		return false;
	}  

	public Position[] getPos(){
		Position[] pos = new Position[minoes.size()*4];
		Tetromino[] t = new Tetromino[minoes.size()];
		
		int h = 0;
		for(int k = 0; k < minoes.size(); k++){
			int j = 0;
			for(Tetromino te : t){
				te = minoes.get(h);
				System.out.println(te.toString());
				pos[h] = new Position(te.getPos(j));
				h++;
				j++;
			}
		}
		
		return pos;
	}
	
	public Position getPos(int q){
		Position[] pos = new Position[minoes.size()*4];
		Tetromino[] t = new Tetromino[minoes.size()];
		
		int h = 0;
		for(int k = 0; k < minoes.size(); k++){
			int j = 0;
			for(Tetromino te : t){
				te = minoes.get(h);
				pos[h] = new Position(te.getPos(j));
				h++;
				j++;
			}
		}
		
		return pos[q];
	}

	public void move(){
		for(int i = 0; i < minoes.size(); i++){
			minoes.get(i).update();
		}
		
	}

	public int getSize(){
		return minoes.size()*4;
	}
}
