package tetrix.view;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;

import tetrix.util.Util;
import tetrix.view.StateHandler.States;

/**
 * Class responsible for the intro sequence where the user is expected to "press start".
 * @author Linus Karlsson
 *
 */
public class IntroView extends BasicGameState{

	private int stateID;
	
	private Image background;
	private Image clickHere;

	private int clickHereXpos;
	private int clickHereYpos;
	private int alphaValue;
	
	private List<Rectangle> pixelStorm;
	
	/**
	 * Making the "Press Any Key" text blink at a given rate
	 */
	private Timer blinkTimer;
	
	/**
	 * Making the pixel blocks fall down at a given rate
	 */
	private Timer pixelStormTimer;
	
	public IntroView(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		background = new Image("img/introbackg.png");
		clickHere = new Image("img/press_any_key.png");
		clickHereXpos = Util.WINDOW_WIDTH/2 - clickHere.getWidth()/2;
		clickHereYpos = 310;
		alphaValue = 100;
		
		blinkTimer = new Timer();
	    blinkTimer.scheduleAtFixedRate(new TimerTask() {
	        public void run() {
	            try {
					blink();
				} catch (SlickException e) {
					e.printStackTrace();
				} 
	          }
	        }, 1000, 1000);
	    
	    pixelStormTimer = new Timer();
	    pixelStormTimer.scheduleAtFixedRate(new TimerTask() {
	        public void run() {
					System.out.print("Hej");
	          }
	        }, 500, 500);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw(0,0);
		clickHere.draw(clickHereXpos, clickHereYpos);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		Input input = gc.getInput();

		if (input.isKeyPressed(Input.KEY_ENTER) ){
			sbg.enterState(States.MAINMENUVIEW.getID());
		}
	}

	@Override
	public int getID() {
		return stateID;
	}
	
	public void blink() throws SlickException {
		if(alphaValue == 100) {
			clickHere.setAlpha(0);
			alphaValue = 0;
		} else {
			clickHere.setAlpha(100);
			alphaValue = 100;
		}
		
	}
}

