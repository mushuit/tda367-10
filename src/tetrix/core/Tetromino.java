package tetrix.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

import tetrix.util.Util;

public abstract class Tetromino implements ActionListener{
	private Square[] square;
	private int startX;
	private int fallspeed;
	private boolean isMoving;
	private int leftIn;
	private Timer timer;


	public Tetromino(int startX){
		this(startX, (Util.WINDOW_WIDTH-Util.BOX_WIDTH)/2);
	}

	public Tetromino(int startX, int leftIn){
		this(startX, leftIn, Util.SQUARE_SIZE);
	}

	public Tetromino(int startX, int leftIn, int fallspeed) {
		this.startX = startX;
		this.fallspeed = fallspeed;
		square = new Square[4];
		this.leftIn = leftIn;
		isMoving = true;
		timer = new Timer(250, this);
		build();

	}

	public void build(){

	}

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

	public int getStartX(){
		return startX;
	}

	public int getLeftIn(int xValue){
		return (leftIn + xValue);
	}

	public void actionPerformed(ActionEvent e) {
		for(Square s : square){
			s.falling();
		}
		timer.stop();

	}
	
	public boolean isPainted(int x, int y){
		return false;
		
	}

}
