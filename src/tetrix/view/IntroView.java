package tetrix.view;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;

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
import tetrix.sound.GameMusic;
import tetrix.util.Util;
import tetrix.view.StateHandler.States;
import tetrix.view.theme.ThemeHandler;

/**
 * Class responsible for the intro sequence where the user is expected to press
 * a key (or enter a secret code).
 * 
 * @author Linus Karlsson
 * 
 */
public class IntroView extends BasicGameState implements KeyListener {

	private int stateID;

	private Image background;
	private Image pressAnyKey;
	private Image tetrixLogo;

	private int alphaValue;
	private boolean isKeyPressed;
	private PixelRain pixelRain;
	private Color pixelColor;
	private boolean isKonamiEntered;

	private LinkedList<Integer> konamiCode;

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
		isKeyPressed = false;
		pixelColor = Color.white;
		pixelRain = new PixelRain();
		isKonamiEntered = false;
		repeatBlink();

		// The konami code as integers (Up, Up, Down, Down, Left, Right, Left,
		// Right, B, A).
		konamiCode = new LinkedList<Integer>(Arrays.asList(200, 200, 208, 208,
				203, 205, 203, 205, 48, 30));
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw(0, 0);

		g.setColor(pixelColor);
		for (int i = 0; i < pixelRain.getList().size(); i++) {
			g.fill(pixelRain.getList().get(i));
		}

		tetrixLogo.draw(0, 200);
		pressAnyKey.draw(Util.WINDOW_WIDTH / 2 - pressAnyKey.getWidth() / 2,
				310);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int rate)
			throws SlickException {

		// Remove all key presses to prevent errors in other states
		Input input = gc.getInput();
		input.clearKeyPressedRecord();

		if (isKeyPressed) {
			StateHandler.addStates(sbg);
			StateHandler.initStates(gc, sbg);
			sbg.enterState(States.MAINMENUVIEW.getID(),
					new FadeOutTransition(), new FadeInTransition());
		} else if (isKonamiEntered) {
			ThemeHandler.setUnderworldTheme();
			try {
				GameMusic.instance().changeSong();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			sbg.getCurrentState().init(gc, sbg);
			pixelRain.upsideDown();
			setPixelColor(Color.black);
		}

		pixelRain.movePixel(rate);
	}

	@Override
	public int getID() {
		return stateID;
	}

	public void blink() throws SlickException {
		if (alphaValue == 100) {
			pressAnyKey.setAlpha(0);
			alphaValue = 0;
		} else {
			pressAnyKey.setAlpha(100);
			alphaValue = 100;
		}
	}

	@Override
	public void keyPressed(int key, char c) {
		try {
			checkKonami(key);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Konami Code is a "cheat" where the user has to enter a combination of key
	 * presses. If the keypresses are correct, a hidden theme will show up
	 * 
	 * @throws SlickException
	 */
	public void checkKonami(int key) throws SlickException {
		try {
			if (key != konamiCode.removeFirst()) {
				isKeyPressed = true;
			}
		} catch (NoSuchElementException e) {
		}

		if (konamiCode.isEmpty()) {
			isKonamiEntered = true;
		}
	}

	public void setPixelColor(Color color) {
		pixelColor = color;
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