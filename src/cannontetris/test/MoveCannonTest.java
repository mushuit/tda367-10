package cannontetris.test;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class MoveCannonTest extends BasicGame {
	private Image cannon = null;
	private float xPos = 10;
	private float yPos = 500;
	
	public MoveCannonTest() {
		super("MoveCannonTest");
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new MoveCannonTest());
            app.setDisplayMode(500, 600, false);
            app.setShowFPS(true); 
            //app.setMaximumLogicUpdateInterval(140);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		cannon.draw(xPos, yPos);
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		cannon = new Image("img/cannon.png");
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_LEFT)) {
	        xPos--;
	    }
		else if(input.isKeyDown(Input.KEY_RIGHT)) {
			xPos++;
		}
	}
}
