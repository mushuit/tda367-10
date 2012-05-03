package tetrix.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

import org.newdawn.slick.SlickException;

import tetrix.util.Util;

public abstract class Tetromino implements ActionListener{
	private Square[] square;
	private int startX;
	private int fallspeed;
	private boolean isMoving;
	private int leftIn;
	private Timer timer;
	private boolean whole;
	protected BlockBox bBox;



	public Tetromino(int startX, BlockBox bBox){
		this(startX, (Util.WINDOW_WIDTH-Util.BOX_WIDTH)/2, bBox);
	}

	public Tetromino(int startX, int leftIn, BlockBox bBox){
		this(startX, leftIn, Util.SQUARE_SIZE, bBox);
	}

	public Tetromino(int startX, int leftIn, int fallspeed, BlockBox bBox) {
		whole = true;
		this.startX = startX;
		this.fallspeed = fallspeed;
		square = new Square[4];
		this.leftIn = leftIn;
		isMoving = true;
		timer = new Timer(250, this);
		this.bBox = bBox;
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
		for(Square s : square){
			if(whole && (bBox.isPainted(s.getX(), s.getY()) || s.getY() > Util.WINDOW_HEIGHT-Util.B4_BOX_HEIGHT-(Util.SQUARE_SIZE*2))){
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

	public boolean isWhole(){
		return whole;
	}

	public void notWhole(){
		whole = false; 
		System.out.println("It broke!");
	}


}
