package tetrix.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import tetrix.util.Util;

public class Square implements ActionListener{
	private Position pos;
	private boolean destroyed;
	private boolean isMoving;
	private Timer timer;
	
	public Square(Position pos) {
		this.pos = pos;
		isMoving = true;
		timer = new Timer(250, this);
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
