package tetrix.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class TestBullets implements Game{
	private Bullet bullet;
	private List bulletin;
	private Iterator<Bullet> itBullets;
	private PowerBullet pBullet;
	private Position pos;
	private boolean power;
	private boolean shot;
	private int bullets;
	private Cannon cannon;
	private float bulletRotation;
	private boolean down;
	private boolean up;
	private Image cannonImage;


	public TestBullets(){
		power = false;
		shot = false;
		bullets = 0;
	}

	public static void main(String[] args){
		try {
			AppGameContainer app = new AppGameContainer(new TestBullets());
			app.setDisplayMode(500, 600, false);
			app.setTargetFrameRate(60);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean closeRequested() {
		return true;
	}

	@Override
	public String getTitle() {
		return "Test for Bullets";
	}

	public void init(GameContainer arg0) throws SlickException {
		pos = new Position(0,300);
		bulletin = new ArrayList();
		cannon = new Cannon(0,300);
		bulletRotation = 0;
		down = false;
		up = false;
		cannonImage = new Image("img/cannon2.png");
	}

	public void update(GameContainer container, int arg1) throws SlickException {

		pos = new Position(cannon.getPosition());
		Input input = container.getInput();
		
		

		if(input.isKeyPressed(Input.KEY_SPACE)){
			bullet = new Bullet(pos, new Image("img/Bullet2.jpg"), bulletRotation);
			bullet.setRotation(bulletRotation);
			bulletin.add(bullet);
			shot = true;
			power = false;
			bullets++;
		}

		for(int i = 0; i < bullets; i++){
			if(!(((Bullet) bulletin.iterator().next()).getGoing())){
				bulletin.remove(i);
				bullets--;
			}
		}
		/*
		if(input.isKeyPressed(Input.KEY_ENTER)){
			pBullet = new PowerBullet(pos);
			shot = true;
			power = true;
		}*/

		if(input.isKeyDown(Input.KEY_RIGHT)){
			cannon.move(1);
		}

		if(input.isKeyDown(Input.KEY_LEFT)){
			cannon.move(-1);
		}

		if(shot && power)
			pBullet.update();
		for(int i = 0; i < bullets; i++)
			if(shot && !power)
				((Bullet) bulletin.get(i)).update();
	}

	public void render(GameContainer arg0, Graphics g) throws SlickException {
		g.setBackground(Color.white);
		g.setColor(Color.white);
		if(!up)
			for(int i = 0; i < bullets; i++)
				if(!power && shot){
					bullet.draw((float) (((Bullet) bulletin.get(i)).getPos().getX()-100), ((Bullet) bulletin.get(i)).getPos().getY(), 150, 50);
				}

		if(up)
			for(int i = 0; i < bullets; i++)
				if(!power && shot){
					bullet.draw((float) (((Bullet) bulletin.get(i)).getPos().getX()-195), ((Bullet) bulletin.get(i)).getPos().getY(), 150, 50);
				}



		g.setColor(Color.blue);
		if(power && shot)
			g.fillRect(pBullet.getPos().getX(), pBullet.getPos().getY(), 10, 10);
		
		cannonImage.draw(cannon.getX(), cannon.getY());
	}
}
