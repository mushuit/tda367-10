package tetrix.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

import tetrix.util.Util;

public abstract class Tetromino implements ActionListener, PropertyChangeListener{
	private Square[] square;
	private int startX;
	private int fallspeed;
	private boolean isMoving;
	private int leftIn;
	private Timer timer;
	public PropertyChangeSupport pcs;
	private boolean whole;



	public Tetromino(int startX){
		this(startX, (Util.WINDOW_WIDTH-Util.BOX_WIDTH)/2);
	}

	public Tetromino(int startX, int leftIn){
		this(startX, leftIn, Util.SQUARE_SIZE);
	}

	public Tetromino(int startX, int leftIn, int fallspeed) {
		whole = true;
		this.startX = startX;
		this.fallspeed = fallspeed;
		square = new Square[4];
		this.leftIn = leftIn;
		isMoving = true;
		timer = new Timer(250, this);
		pcs = new PropertyChangeSupport(this);
		pcs.addPropertyChangeListener(this);
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

	public abstract void propertyChange(PropertyChangeEvent e);

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
			if(whole){
				if(s.isMoving())
					s.falling();
			}
			else{
				if(!s.destroyed()){
					s.falling();
				}
			}

		}
		timer.stop();

	}

	public boolean isPainted(int x, int y){
		return false;

	}

	public boolean isWhole(){
		return whole;
	}

	public void notWhole(){
		whole = false; 
		System.out.println("It broke!");
	}


}
