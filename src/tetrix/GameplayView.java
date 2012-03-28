package tetrix;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tetrix.core.Bullet;
import tetrix.core.Cannon;
import tetrix.core.Util;

public class GameplayView extends BasicGameState {

	private int stateID;

	private Image background;
	private Image cannonImage;
	private Cannon cannon;
	private List bulletList;
	private Bullet bullet; 
	private Image bulletImage;

	public GameplayView(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		background= new Image("img/game-background.png");
		cannonImage = new Image("img/cannon2.png");
		cannon = new Cannon(225,525);
		bulletList = new ArrayList();
		bulletImage = new Image("img/Bullet2.jpg");
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		background.draw(0,0);
		cannonImage.draw(cannon.getX(), cannon.getY());

		arg2.setColor(Color.black);
		for(int i = 0; i < bulletList.size(); i++){
			arg2.fillRect(((Bullet) bulletList.get(i)).getX(), ((Bullet) bulletList.get(i)).getY(), 5, 5);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		int updateSpeed = 1000 /Util.FPS;

		if(input.isKeyDown(Input.KEY_RIGHT)) {
			cannon.move(updateSpeed);
		}

		if(input.isKeyDown(Input.KEY_LEFT)) {
			cannon.move(-updateSpeed);
		}

		if(input.isKeyDown(Input.KEY_SPACE)) {
			bullet = new Bullet(cannon.getPosition(), cannon.getValue());
			bulletList.add(bullet);
		}


		for(int i = 0; i < bulletList.size(); i++){
			((Bullet) bulletList.get(i)).update();
			
			if(!((Bullet) bulletList.get(i)).getGoing())
				bulletList.remove(i);

		}




		cannonImage.setRotation(cannon.getRotation());
	}

	@Override
	public int getID() {
		return stateID;
	}

}
