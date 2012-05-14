package tetrix.core.tetrominos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import org.newdawn.slick.SlickException;

import tetrix.core.BlockBox;
import tetrix.core.Position;
import tetrix.util.Util;

/**
 * 
 * @author magnus huttu junghard
 *
 */

public abstract class Tetromino implements ActionListener{
	private Square[] square;
	private int startX;
	private int fallspeed;
	private boolean isMoving;
	private int leftIn;
	private Timer timer;
	protected boolean newBlock;
	protected BlockBox bBox;
	private int l;
	private boolean stop;
	private int anInt;
	protected boolean SqrDstr;
	protected boolean SqrDstr2;
	protected boolean SqrDstr3;
	protected boolean SqrDstr4;
	protected boolean used;




	public Tetromino(int startX, BlockBox bBox){
		this(startX, (Util.WINDOW_WIDTH-Util.BOX_WIDTH)/2, bBox);
	}

	public Tetromino(int startX, int leftIn, BlockBox bBox){
		this(startX, leftIn, Util.SQUARE_SIZE, bBox);
	}

	public Tetromino(int startX, int leftIn, int fallspeed, BlockBox bBox) {
		newBlock = false;
		this.startX = startX;
		this.fallspeed = fallspeed;
		square = new Square[4];
		this.leftIn = leftIn;
		isMoving = true;
		timer = new Timer(250, this);
		this.bBox = bBox;
		newBlock = true;
		stop = false;
		l = 0;
		anInt = 10;
		SqrDstr = false;
		SqrDstr2 = false;
		SqrDstr3 = false;
		SqrDstr4 = false; 
		used = false;
		build();
	}

	public abstract void build();

	public void update(){
		timer.start();
	}

	public Position[] getPos(){
		int o = 0;
		for(Square s : square){
			if(!s.destroyed())
				o++;
		}
		Position[] pos = new Position[o];
		int i = 0;
		for(Square s : square){
			if(!s.destroyed()){
				pos[i] = s.getPos();
				i++;
			}
		}

		return pos.clone();
	}


	public Square[] getSquares(){
		return square;
	}

	public void stop(){
		isMoving = false;
		for(Square s : this.getSquares()){
			s.stop();
		}
	}

	public boolean isMoving(){
		return isMoving;
	}

	public void startMoving(){
		isMoving = true;
	}

	public int getStartX(){
		return startX;
	}

	public int getLeftIn(int xValue){
		return (leftIn + xValue);
	}

	public void actionPerformed(ActionEvent e) {
		//easy level has the int 0
		if(bBox.level() == 0){
			for(int i = 3; i > -1; i--){
				if((bBox.isPainted(square[i].getX(), square[i].getY()) || square[i].getY() > Util.WINDOW_HEIGHT-Util.B4_BOX_HEIGHT-(Util.SQUARE_SIZE*2))){
					square[i].stop();
				}

			}

			int o = 0;
			for(int i = 3; i > -1; i--){
				if(square[i].isMoving()){
					square[i].falling();
				}
				else{
					o++;
				}
			}
			if(o == 4){
				stop();
			}
			timer.stop();
		}

		//harder level has bigger int
		if(bBox.level() == 1){

			for(int j = 3; j > -1; j--){
				if(bBox.isPainted(this)){
					stop = true;
				}

				if(isMoving()){
					square[j].falling();
				}

				if(newBlock()){
					usedBlock();

					try {
						notWhole();
					} catch (SlickException e1) {
						System.out.println("Exception in Tetromino");
						e1.printStackTrace();
					}
				}

			}
			if(stop){
				stop();
			}

			timer.stop();
		}

		for(int i = 0; i < 4; i++){
			if(!square[i].isMoving() && square[i].getY() == Util.B4_BOX_HEIGHT+Util.SQUARE_SIZE){
				bBox.gameIsOver();
			}
		}
	}


	public abstract void notWhole() throws SlickException;

	public boolean newBlock(){
		return newBlock;
	}

	public void usedBlock(){
		if(l == 0){
			newBlock = false;
			System.out.println("used");
			l++;
		}
		else
			newBlock = true;
	}

	public int getX(){
		return startX;
	}

	public abstract String toString();
}

