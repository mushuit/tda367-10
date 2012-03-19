package tetrix.core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class CannonMoveTest extends BasicGame {

	private Cannon cannon;
	
	public CannonMoveTest() {
		super("Cannon Move Test");
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
        cannon.draw(cannon.getX(), cannon.getY());
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		cannon = new Cannon(30,40, new Image("img/cannon.png"));
	}

	@Override
	public void update(GameContainer gc, int arg) throws SlickException {
		
	}
	
	public static void main(String[] args) 
			throws SlickException
    {
         AppGameContainer app = 
			new AppGameContainer(new CannonMoveTest());
 
         app.setDisplayMode(500, 600, false);
         app.start();
    }

}
