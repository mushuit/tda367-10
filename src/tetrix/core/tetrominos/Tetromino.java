package tetrix.core.tetrominos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

import org.newdawn.slick.SlickException;

import tetrix.core.BlockBox;
import tetrix.core.Position;
import tetrix.util.Util;

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
		l = 0;
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
			for(Square s : square){
				if((bBox.isPainted(s.getX(), s.getY()) || s.getY() > Util.WINDOW_HEIGHT-Util.B4_BOX_HEIGHT-(Util.SQUARE_SIZE*2))){
					s.stop();
					System.out.println(bBox.isPainted(s.getX(), s.getY()) + " Tetromino " + s.getY());

				}
				if(s.isMoving())
					s.falling();

			}

			int i = 0;
			for(Square s : square){
				if(!s.isMoving())
					i++;
			}
			if(i == 4){
				stop();
			}
			timer.stop();
		}

		//harder level has bigger int
		if(bBox.level() == 1){
			for(Square s : square){
				if((bBox.isPainted(s.getX(), s.getY()) || s.getY() > Util.WINDOW_HEIGHT-Util.B4_BOX_HEIGHT-(Util.SQUARE_SIZE*2))){
					stop();

				}
				if(isMoving())
					s.falling();

			}

			int i = 0;
			for(Square s : square){
				if(!s.isMoving()){
					i++;
				}
				System.out.println(s.destroyed() + "  " + !s.used());
				if(s.destroyed() && !newBlock()){
					usedBlock();
					try {
						System.out.println("notWhole() anropas");
						notWhole();
					} catch (SlickException e1) {
						System.out.println("Exception in Tetromino");
						e1.printStackTrace();
					}
				}

			}
			if(i == 4){
				stop();
			}
			timer.stop();
		}
	}

	public abstract void notWhole() throws SlickException;

	public boolean newBlock(){
		return newBlock;
	}

	public void usedBlock(){
		System.out.println("used");
		if(l == 0){
			newBlock = false;
			l++;
		}
		else
			newBlock = true;
		
	}
}

