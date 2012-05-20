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
	private Position position;
	private int bulletPosition = 0;
	private boolean moving = false;
	private int positionValue;
	private boolean isStill;

	public Bullet(int xPos, int yPos) {
		position = new Position(0, 0);
		position.setX(xPos);
		position.setY(yPos);
		shoot();
		isStill = false;
	}

	public Bullet(Position pos, int positionValue) {
		this.positionValue = positionValue;
		this.position = new Position(pos.getX(), pos.getY());
		shoot();
		setRotation(-90);
		isStill = false;
	}

	public Bullet(Position pos, Image img, float rotation, int positionValue) {
		super(img);
		rotate(rotation);
		this.positionValue = positionValue;
		this.position = new Position(pos.getX(), pos.getY());
		shoot();
		setRotation(-90);
		isStill = false;
	}

	private void shoot() {

		System.out.println(position.getX() + "    " + position.getY());

		if (positionValue >= 300 && positionValue <= 800) {
			bulletPosition = 1;
			position.setY(position.getY() + 22.5);
		}

		else if (positionValue >= 0 && positionValue <= 300) {
			bulletPosition = 2;
			position.setX(position.getX() + 22.5);
		}

		else if (positionValue >= 800 && positionValue <= 1100) {
			bulletPosition = 3;
			position.setX((int) (position.getX() + 22.5));
			position.setY(position.getY() + 50);
		}

		else if (positionValue >= 1100 && positionValue <= 1600) {
			bulletPosition = 4;
			position.setY((int) (position.getY() + 22.5));
			position.setX(position.getX() + 50);
		}

		moving = true;
	}

	public void stop() {
		isStill = true;
	}

	public Position getPos() {
		return position;
	}

	public int getX() {
		return (int) position.getX();
	}

	public int getY() {
		return (int) position.getY();
	}

	public boolean getMoving() {
		return moving;
	}

	public boolean isMoving() {
		return !isStill;
	}

	public void update() {
		if (moving) {

			switch (bulletPosition) {

			case 1:
				position.setX(position.getX() - 10);
				if (position.getX() < 0)
					moving = false;
				break;

			case 2:
				position.setY(position.getY() - 10);
				if (position.getY() < 0)
					moving = false;
				break;

			case 3:
				position.setY(position.getY() + 10);
				if (position.getY() > Util.WINDOW_HEIGHT)
					moving = false;
				break;

			case 4:
				position.setX(position.getX() + 10);
				if (position.getX() > Util.WINDOW_WIDTH)
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
