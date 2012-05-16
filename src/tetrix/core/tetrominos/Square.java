package tetrix.core.tetrominos;

/**
 * 
 * @author Magnus Huttu
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import tetrix.core.Position;
import tetrix.util.Util;

public class Square implements ActionListener {
	private Position pos;
	private boolean destroyed;
	private boolean isMoving;
	private Timer timer;
	private Tetromino t;
	private int whichSqr;
	private boolean isSplit;
	private boolean used;

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
		isSplit = false;
		used = false;
	}

	public Position getPos() {
		return new Position(pos);
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public void setY(int f) {
		pos.setY(f);
	}

	public void setX(int f) {
		pos.setX(f);
	}

	public int getY() {
		return pos.getY();
	}

	public int getX() {
		return pos.getX();
	}

	public boolean destroyed() {
		return destroyed;
	}

	public void destroy() {
		t.usedBlock();
		destroyed = true;

	}

	public void unDestroy() {
		destroyed = false;

	}

	public void use() {
		used = true;
	}

	public void unUse() {
		used = false;
	}

	public boolean used() {
		return used;
	}

	public void stop() {
		isMoving = false;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void startMoving() {
		isMoving = true;
	}

	public void update() {
		timer.start();
	}

	public void falling() {
		if (isMoving()
				&& this.getY() < Util.B4_BOX_HEIGHT + Util.BOX_HEIGHT
						- Util.SQUARE_SIZE)
			setY(pos.getY() + Util.SQUARE_SIZE);
	}

	public void rowFall() {
		if (this.getY() < Util.B4_BOX_HEIGHT + Util.BOX_HEIGHT
				- Util.SQUARE_SIZE)
			setY(pos.getY() + Util.SQUARE_SIZE);
	}

	public void actionPerformed(ActionEvent e) {
		falling();
		timer.stop();
	}

	public int getNbr() {
		return whichSqr;
	}
}
