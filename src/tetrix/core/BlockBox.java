package tetrix.core;

/**
 * 
 * @author Magnus Huttu
 *
 */


import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;

import tetrix.core.tetrominos.I;
import tetrix.core.tetrominos.J;
import tetrix.core.tetrominos.L;
import tetrix.core.tetrominos.O;
import tetrix.core.tetrominos.S;
import tetrix.core.tetrominos.Square;
import tetrix.core.tetrominos.Tetromino;
import tetrix.core.tetrominos.Z;
import tetrix.util.Util;

public class BlockBox {

	private int nbrOfColumns;
	private int nbrOfRows;
	private List<Tetromino> minoes;
	private boolean isInUse;
	private boolean rowFilled;
	private TetrominoFactory tF;
	private int level;

	public BlockBox() throws SlickException {
		this(10,20);
	}

	public BlockBox(int nbrOfColumns, int nbrOfRows) throws SlickException{
		this.nbrOfColumns = nbrOfColumns;
		this.nbrOfRows = nbrOfRows;
		isInUse = false;
		minoes = new ArrayList<Tetromino>();
		rowFilled = false;
		tF = new TetrominoFactory();
		level = 1;
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
			if(t.isMoving())
				t.update();

		}
//		for(int i = 0; i < minoes.size(); i++){
//			if(!minoes.get(i).newBlock()){
//				System.out.println("notWhole()");
//				minoes.get(i).notWhole();
//				minoes.get(i).usedBlock();
//			}
//		}

		//kollar efter hela rader
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
							//if(!t.isMoving())
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

	public void newBlock() throws SlickException{	
		isInUse = true;
		tF.createRandomTetromino(this);
	}

	public void newBlock(int i) throws SlickException{
		isInUse = true;
		tF.createTetromino(this, 0);
	}

	/**
	 * Create a new block with more data.
	 *
	 * @param i represents which type of block that is supposed to be created
	 * @param sqrDestroyed represens which square that has been destroyed
	 * @param pos represents where the block should start
	 * @throws SlickException
	 */
	public void newBlock(int i, int sqrDestroyed, Position pos) throws SlickException{
		isInUse = true;
		tF.createBrokenTetromino(this, i, sqrDestroyed, pos);
	}

	public void addMino(Tetromino t){
		minoes.add(t);
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

	public boolean isRowFilled() {
		return rowFilled;
	}
	
	/**
	 * There are 2 levels at the moment, level 0 and level 1, 0 represents easy-mode and 1 represents hard-mode.
	 * @param i sets which level that is supposed to be played
	 */
	public void setLevel(int i){
		level = i;
	}

	public int level(){
		return level;
	}

}
