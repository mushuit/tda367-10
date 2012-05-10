package tetrix.view;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import tetrix.core.PixelRain;
import tetrix.util.Util;
import tetrix.util.theme.ThemeHandler;
import tetrix.view.StateHandler.States;

/**
 * Class responsible for the intro sequence where the user is expected to "press start".
 * @author Linus Karlsson
 *
 */
public class IntroView extends BasicGameState implements KeyListener{

	private int stateID;
	
	private Image background;
	private Image pressAnyKey;
	private Image tetrixLogo;
	
	private int alphaValue;
	private Random rand;
	private boolean isKeyPressed;
	private PixelRain pixelRain;
	
	public IntroView(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		background = ThemeHandler.get(ThemeHandler.BACKGROUND_IMG);
		tetrixLogo = ThemeHandler.get(ThemeHandler.TETRIX_LOGO_IMG);
		pressAnyKey = ThemeHandler.get(ThemeHandler.PRESS_ANY_KEY_IMG);
		alphaValue = 100;
		rand = new Random();
		isKeyPressed = false;
		pixelRain = new PixelRain();
		repeatBlink();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw(0,0);
		
		g.setColor(Color.white);
		for(int i = 0; i< pixelRain.getList().size();i++) {
			g.fill(pixelRain.getList().get(i));
		}
		
		tetrixLogo.draw(0, 200);
		pressAnyKey.draw(Util.WINDOW_WIDTH/2 - pressAnyKey.getWidth()/2, 310);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int rate)
			throws SlickException {
		
		// Remove all key presses to prevent errors in other states
		Input input = gc.getInput();
		input.clearKeyPressedRecord();
		
		if (isKeyPressed) {
			leave(gc, sbg);
			sbg.enterState(States.MAINMENUVIEW.getID(), new FadeOutTransition(), new FadeInTransition());
		}
		
		pixelRain.movePixel(rate);
	}

	@Override
	public int getID() {
		return stateID;
	}
	
	public void blink() throws SlickException {
		if(alphaValue == 100) {
			pressAnyKey.setAlpha(0);
			alphaValue = 0;
		} else {
			pressAnyKey.setAlpha(100);
			alphaValue = 100;
		}
	}
	
	public Color randomColor() {
		return new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
	}
	
	@Override
	public void keyPressed(int key, char c){
		isKeyPressed = true;
	}
	
	public void repeatBlink() {
	    new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					blink();
					Thread.sleep(1000);
					repeatBlink();
	            } catch (SlickException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}