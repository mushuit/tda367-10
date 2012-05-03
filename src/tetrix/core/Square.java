package tetrix.core;

/**
 * 
 * @author Magnus Huttu
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import tetrix.util.Util;

public class Square implements ActionListener{
	private Position pos;
	private boolean destroyed;
	private boolean isMoving;
	private Timer timer;
	private Tetromino t;
	private int whichSqr;

	public Square(Position pos) {
		this.pos = pos;
		isMoving = true;
		timer = new Timer(250, this);
	}

	public Square(Position pos, Tetromino t) {
		this.pos = pos;
		isMoving = true;
		timer = new Timer(250, this);
		this.t = t;
	}

	public Square(Position pos, Tetromino t, int whichSqr) {
		this.pos = pos;
		isMoving = true;
		timer = new Timer(250, this);
		this.t = t;
		this.whichSqr = whichSqr;
	}
	
	public Position getPos(){
		return new Position(pos);
	}
	
	public void setPos(Position pos){
		this.pos = pos;
	}
	
	public void setY(int f){
		pos.setY(f);
	}

	public int getY(){
		return pos.getY();
	}

	public int getX() {
		return pos.getX();
	}
	
	public boolean destroyed(){
		return destroyed;
	}
	
	public void destroy(){
		destroyed = true;
		
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
	
	public void update(){
		timer.start();
	}

	public void falling(){
		setY(pos.getY()+Util.SQUARE_SIZE);
	}

	public void actionPerformed(ActionEvent e) {
		falling();
		timer.stop();
	}
}
