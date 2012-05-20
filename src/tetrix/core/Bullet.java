package tetrix.core;

import org.newdawn.slick.Image;

import tetrix.util.Util;

/**
 * Class representing a bullet which is fired by the cannon on user input.
 * 
 * @author Magnus Huttu
 * 
 */
public class Bullet extends Image {
	private Position pos;
	private int bulletPosition = 0;
	private boolean moving = false;
	private int positionValue;
	private boolean stop;

	public Bullet(int xPos, int yPos) {
		pos = new Position(0, 0);
		pos.setX(xPos);
		pos.setY(yPos);
		shoot();
		stop = false;
	}

	public Bullet(Position pos, int positionValue) {
		this.positionValue = positionValue;
		this.pos = new Position(pos.getX(), pos.getY());
		shoot();
		setRotation(-90);
		stop = false;
	}

	public Bullet(Position pos, Image img, float rotation, int positionValue) {
		super(img);
		rotate(rotation);
		this.positionValue = positionValue;
		this.pos = new Position(pos.getX(), pos.getY());
		shoot();
		setRotation(-90);
		stop = false;
	}

	private void shoot() {

		System.out.println(pos.getX() + "    " + pos.getY());

		if (positionValue >= 300 && positionValue <= 800) {
			bulletPosition = 1;
			pos.setY(pos.getY() + 22.5);
		}

		else if (positionValue >= 0 && positionValue <= 300) {
			bulletPosition = 2;
			pos.setX(pos.getX() + 22.5);
		}

		else if (positionValue >= 800 && positionValue <= 1100) {
			bulletPosition = 3;
			pos.setX((int) (pos.getX() + 22.5));
			pos.setY(pos.getY() + 50);
		}

		else if (positionValue >= 1100 && positionValue <= 1600) {
			bulletPosition = 4;
			pos.setY((int) (pos.getY() + 22.5));
			pos.setX(pos.getX() + 50);
		}

		moving = true;
	}

	public void stop() {
		stop = true;
	}

	public Position getPos() {
		return pos;
	}

	public int getX() {
		return (int) pos.getX();
	}

	public int getY() {
		return (int) pos.getY();
	}

	public boolean getMoving() {
		return moving;
	}

	public boolean isMoving() {
		return !stop;
	}

	public void update() {
		if (moving) {

			switch (bulletPosition) {

			case 1:
				pos.setX(pos.getX() - 10);
				if (pos.getX() < 0)
					moving = false;
				break;

			case 2:
				pos.setY(pos.getY() - 10);
				if (pos.getY() < 0)
					moving = false;
				break;

			case 3:
				pos.setY(pos.getY() + 10);
				if (pos.getY() > Util.WINDOW_HEIGHT)
					moving = false;
				break;

			case 4:
				pos.setX(pos.getX() + 10);
				if (pos.getX() > Util.WINDOW_WIDTH)
					moving = false;
				break;

			default:
				break;

			}
		}
	}

	public void rotate(float i) {
		this.setRotation(i);
	}
}
