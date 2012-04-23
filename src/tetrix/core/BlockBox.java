package tetrix.core;


import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import tetrix.util.Util;

public class BlockBox {

	private int width;
	private int height;
	private int yPos;
	private int xPos;
	private List<Tetromino> minoes;
	private List<Tetromino> fMinoes;
	private boolean inUse;
	private int putIntoFminoes;

	private Image img;

	public BlockBox() throws SlickException {
		this(10,20);
	}

	public BlockBox(int width, int height) throws SlickException{
		this.width = width;
		this.height = height;
		inUse = false;
		minoes = new ArrayList();
		fMinoes = new ArrayList();
		putIntoFminoes = 0;

		img = new Image("img/block.png");

		clearBoard();
	}

	public void clearBoard() {
		minoes.clear();
		fMinoes.clear();
	}

	public void update() throws SlickException{
		for(Tetromino t : minoes){
			for(Position p : t.getPos()){
				if(isPainted(p.getX(), p.getY())){
					t.stop();
				}
			}
			if(!t.isMoving())
				t.update();
		}
	}

	public void newBlock(int i) throws SlickException{
		inUse = true;

		switch(i){

		case 1:
			minoes.add(new L((int)(Math.random()*8), (Util.WINDOW_WIDTH-Util.BOX_WIDTH)/2, Util.squareSize));
			break;

		case 2:
			minoes.add(new J((int)(Math.random()*8), (Util.WINDOW_WIDTH-Util.BOX_WIDTH)/2, Util.squareSize));
			break;

		case 3:
			minoes.add(new O((int)(Math.random()*9), (Util.WINDOW_WIDTH-Util.BOX_WIDTH)/2, Util.squareSize));
			break;

		case 4:
			minoes.add(new I((int)(Math.random()*7), (Util.WINDOW_WIDTH-Util.BOX_WIDTH)/2, Util.squareSize));
			break;

		case 5:
			minoes.add(new Z((int)(Math.random()*8), (Util.WINDOW_WIDTH-Util.BOX_WIDTH)/2, Util.squareSize));
			break;

		case 6:
			minoes.add(new S((int)(Math.random()*8), (Util.WINDOW_WIDTH-Util.BOX_WIDTH)/2, Util.squareSize));
			break;

		}
	}

	public boolean isPainted(float x, float y) {
		if(y == 440){
			return true;
		}
		for(Tetromino t : minoes){
			Position[] p = t.getPos();
			for(int i = 0; i < 4; i++)
				if(p[0].getY() == y+Util.squareSize && p[i].getX() == x){
					return true;
				}
			if(!t.isMoving())
				t.update();

		}
		return false;
	}

	public Position[][] getPos(){
		Position[][] pos = new Position[minoes.size()][4];

		int h = 0;
		for(Tetromino t : minoes){
			pos[h] = t.getPos();
			h++;
		}

		return pos.clone();
	}


	public boolean inUse(){
		return inUse;
	}
}
