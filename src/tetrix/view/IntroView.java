package tetrix.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;

import tetrix.core.Bullet;
import tetrix.core.ThemeHandler;
import tetrix.sound.SoundEffects;
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
	private Image clickHereMouseOver;

	private int clickHereXpos;
	private int clickHereYpos;
	 
	private boolean inClickHereArea = false;
	private String themeFolder;
	
	public IntroView(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		themeFolder = ThemeHandler.getTheme();
		background = new Image(themeFolder + "introbackg.png");
		clickHere = new Image(themeFolder + "clickHere.png");
		clickHereMouseOver = new Image(themeFolder + "clickHereMouseOver.png");
		clickHereXpos = 200-(clickHere.getWidth()/2);
		clickHereYpos = 414;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw(0,0);
		clickHere.draw(clickHereXpos, clickHereYpos);
		if(inClickHereArea){
			clickHereMouseOver.draw(clickHereXpos, clickHereYpos);
		} else {
			clickHere.draw(clickHereXpos, clickHereYpos);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		Input input = gc.getInput();
		 
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		 
		if( ( mouseX >= clickHereXpos && mouseX <= clickHereXpos + clickHere.getWidth()) &&
			( mouseY >= clickHereYpos && mouseY <= clickHereYpos + clickHere.getHeight()) ){
			inClickHereArea = true;
		} else{
			inClickHereArea = false;
		}
		if(inClickHereArea){
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) ){
				sbg.enterState(States.MAINMENUVIEW.getID());
			}
		}
		if (input.isKeyPressed(Input.KEY_ENTER) ){
			sbg.enterState(States.MAINMENUVIEW.getID());
			SoundEffects.menuClickPlay();
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}

