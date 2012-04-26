package tetrix.core;


import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;

import tetrix.util.Util;

public class BlockBox {

	private int nbrOfColumns;
	private int nbrOfRows;
	private List<Tetromino> minoes;
	private boolean isInUse;
	private boolean rowFilled;

	public BlockBox() throws SlickException {
		this(10,20);
	}

	public BlockBox(int nbrOfColumns, int nbrOfRows) throws SlickException{
		this.nbrOfColumns = nbrOfColumns;
		this.nbrOfRows = nbrOfRows;
		isInUse = false;
		minoes = new ArrayList<Tetromino>();
		rowFilled = false;
		clearBoard();
	}


	public void clearBoard() {
		minoes.clear();
	}

	public void clearRow(int y){
		for(Tetromino t : minoes){
			for(Square s : t.getSquares()){
				if(s.getY() == y+Util.SQUARE_SIZE){
					s.destroy();
				}
			}
		}

		for(Tetromino t : minoes){
			for(Square s : t.getSquares()){
				if(s.getY() < y)
					if(!s.destroyed())
						s.falling();
			}
		}
	}


	public void update() throws SlickException{
		for(Tetromino t : minoes){
			for(Square s : t.getSquares()){
				if(!s.destroyed())
					if(isPainted(s.getX(), s.getY()) || s.getY() > Util.BOX_HEIGHT+50){
						t.stop();
					}
			}
			if(t.isMoving())
				t.update();
		}
		
		for(int y = Util.B4_BOX_HEIGHT-Util.SQUARE_SIZE; y < Util.WINDOW_HEIGHT-Util.B4_BOX_HEIGHT; y+=Util.SQUARE_SIZE){
			int amountFilled = 0;
			for(int x = Util.B4_BOX_WIDTH; x < Util.WINDOW_WIDTH-Util.B4_BOX_WIDTH; x+=Util.SQUARE_SIZE){
				if(isPainted(x, y)){
					amountFilled++;
				}
			}
			if(amountFilled == 10){
				clearRow(y);

			}
		}
	}

	public boolean isPainted(int x, int y) {
		for(Tetromino t : minoes){
			for(Square s : t.getSquares()){
				if(!s.destroyed())
					if(s.getX() == x){
						if(s.getY() == y + Util.SQUARE_SIZE){
							if(!t.isMoving())
								return true;
						}
					}
			}
		}
		return false;
	}

	public List<Tetromino> getTetroList() {
		List<Tetromino> temp = new ArrayList<Tetromino>();
		temp.addAll(minoes);
		return temp;
	}

	public void newBlock(int i) throws SlickException{
		isInUse = true;

		switch(i){

		case 1:
			minoes.add(new L((int)(Math.random()*8)));
			break;

		case 2:
			minoes.add(new J((int)(Math.random()*8)));
			break;

		case 3:
			minoes.add(new O((int)(Math.random()*9)));
			break;

		case 4:
			minoes.add(new I((int)(Math.random()*7)));
			break;

		case 5:
			minoes.add(new Z((int)(Math.random()*8)));
			break;

		case 6:
			minoes.add(new S((int)(Math.random()*8)));
			break;

		}
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

	public boolean isInUse(){
		return isInUse;
	}

	public int getColumns() {
		return nbrOfColumns;
	}

	public int getRows() {
		return nbrOfRows;
	}

}
