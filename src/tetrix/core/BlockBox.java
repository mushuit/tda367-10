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
				if(!t.stopped())
			t.update();
				else
					putInFminoes(t);
		}
		deleteFromMinoes(putIntoFminoes);
	}

	public void newBlock() throws SlickException{
		inUse = true;
		minoes.add(new Tetromino());
	}

	public boolean isPainted(float f, float g) {
		if(g == 480){
			return true;
		}
		for(int i = 0; i < fMinoes.size(); i++){
			for(Position p : fMinoes.get(i).getPos()){
				if(p.getX() == f){
					if(p.getY() == g+1){
						return true;
					}
				}
			}
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

	public void move(){
		for(int i = 0; i < minoes.size(); i++){
			minoes.get(i).update();
		}
	}

	public int getSize(){
		return minoes.size()*4;
	}
	
	public boolean inUse(){
		return inUse;
	}

	public void putInFminoes(Tetromino t){
		fMinoes.add(t);
		putIntoFminoes = 0;
	}

	public void deleteFromMinoes(int i){
		for(int x = 0; x < i; x++){
			minoes.remove(x);
		}
	}
	
}
