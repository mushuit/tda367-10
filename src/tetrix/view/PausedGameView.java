package tetrix.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import tetrix.util.Util;
import tetrix.view.StateHandler.States;
import tetrix.view.theme.ThemeHandler;

/**
 * A class responsible for showing the pause menu. The background image is a copy of the GameplayView
 * @author Linus Karlsson
 *
 */
public class PausedGameView extends BasicGameState {
 
	private int stateID;
	
	private Image background;
	private Image pauseBox;
	private Image resume;
	private Image newGame;
	private Image mainMenu;
	private Image quit; 
	private Image hover;
	
	private int pauseBoxXPos;
	private int resumeYPos;
	private int newGameYPos;
	private int mainMenuYPos;
	private int quitYPos;
	private int hoverYPos;
	
	/**
	 * Value between 0 and 3 to keep track of the user's choices.
	 * 
	 * 0. Resume
	 * 1. New Game
	 * 2. Main Menu
	 * 3. Quit
	 */
	private int hoverValue;
	
	public PausedGameView(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		background = ((GameplayView) sbg.getState(States.GAMEPLAYVIEW.getID())).getPausedScreen();
		hover = ThemeHandler.get(ThemeHandler.HOVER_SMALL_IMG);	
		pauseBox = ThemeHandler.get(ThemeHandler.POPUP_IMG);	
		resume = ThemeHandler.get(ThemeHandler.RESUME_IMG);	
		newGame = ThemeHandler.get(ThemeHandler.NEW_GAME_IMG);	
		mainMenu = ThemeHandler.get(ThemeHandler.MAIN_MENU_IMG);	
		quit = ThemeHandler.get(ThemeHandler.QUIT_IMG);	
		
		resumeYPos = 205;
		newGameYPos = 255;
		mainMenuYPos = 305;
		quitYPos = 355;
		hoverYPos = resumeYPos;
		pauseBoxXPos = (Util.WINDOW_WIDTH/2 - pauseBox.getWidth()/2);
		hoverValue = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw(0,0);
		background.setAlpha(50);
		
		pauseBox.draw(pauseBoxXPos, (Util.WINDOW_HEIGHT/2 - pauseBox.getHeight()/2));
		hover.draw(pauseBoxXPos, hoverYPos);
		resume.draw(pauseBoxXPos, resumeYPos);
		newGame.draw(pauseBoxXPos, newGameYPos);
		mainMenu.draw(pauseBoxXPos, mainMenuYPos);
		quit.draw(pauseBoxXPos, quitYPos);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int rate)
			throws SlickException {
		Input input = gc.getInput();
		
		if(input.isKeyPressed(Input.KEY_DOWN)) {
			hoverValue = (hoverValue + 1) % 4;
		} 
		else if(input.isKeyPressed(Input.KEY_UP)) {
			hoverValue--;
			if(hoverValue < 0) {
				hoverValue = 3;
			}
		}
		
		moveMenuFocus();
		
		if(input.isKeyPressed(Input.KEY_ENTER)) {
			if(hoverValue == 0) {
				input.clearKeyPressedRecord();
				sbg.enterState(States.GAMEPLAYVIEW.getID());
			}
			else if(hoverValue == 1) {
				input.clearKeyPressedRecord();
				((GameplayView) sbg.getState(States.GAMEPLAYVIEW.getID())).newGame();
				sbg.enterState(States.GAMEPLAYVIEW.getID(), new FadeOutTransition(), new FadeInTransition());
			}
			else if(hoverValue == 2) {
				input.clearKeyPressedRecord();
				sbg.getState(States.MAINMENUVIEW.getID()).leave(gc, sbg);
				sbg.getState(States.MAINMENUVIEW.getID()).init(gc, sbg);
				sbg.enterState(States.MAINMENUVIEW.getID(), new FadeOutTransition(), new FadeInTransition());
			}
			else if(hoverValue == 3) {
				gc.exit();
			}
			hoverValue = 0;
		}
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)) {
			input.clearKeyPressedRecord();
			sbg.enterState(States.GAMEPLAYVIEW.getID());
		}
	}
	
	public void moveMenuFocus() {
		switch(hoverValue) {
		case 0:
			hoverYPos = resumeYPos;
			break;
		case 1:
			hoverYPos = newGameYPos;
			break;
		case 2:
			hoverYPos = mainMenuYPos;
			break;
		case 3:
			hoverYPos = quitYPos;
			break;
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
