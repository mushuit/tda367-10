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
 * Class responsible for the main menu view.
 * @author Linus Karlsson
 *
 */
public class MainMenuView extends BasicGameState{

	private int stateID;
	
	private Image background;
	private Image tetrixLogo;
	private Image startGame;
	private Image exit;
	private Image settings;
	private Image highscore;
	private Image menuHover;
	
	private int startGameYPos;
	private int exitYPos;
	private int settingsYPos;
	private int highscoreYPos;
	private int menuXPos;
	private int hoverYPos;
	
	/**
	 * Value between 0 and 3 to keep track of the user's choices.
	 * 
	 * 0. Start Game
	 * 1. Settings
	 * 2. Highscore
	 * 3. Exit
	 */
	private int hoverValue;
	
	public MainMenuView(int stateID) {
        this.stateID = stateID;
    }
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		background = ThemeHandler.get(ThemeHandler.BACKGROUND_IMG);
		tetrixLogo = ThemeHandler.get(ThemeHandler.TETRIX_LOGO_IMG);
		startGame = ThemeHandler.get(ThemeHandler.START_GAME_IMG);
		settings = ThemeHandler.get(ThemeHandler.SETTINGS_IMG);
		exit = ThemeHandler.get(ThemeHandler.EXIT_IMG);
		highscore = ThemeHandler.get(ThemeHandler.HIGHSCORE_IMG);
		menuHover = ThemeHandler.get(ThemeHandler.HOVER_IMG);
		
		hoverValue = 0;
		startGameYPos = 250;
		settingsYPos = 320;
		highscoreYPos = 390;
		exitYPos = 460;
		menuXPos = (Util.WINDOW_WIDTH/2) - (startGame.getWidth()/2);
		hoverYPos = startGameYPos;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw(0,0);
		
		menuHover.draw(menuXPos, hoverYPos);
		tetrixLogo.draw(Util.WINDOW_WIDTH/2-(tetrixLogo.getWidth()/2), 100);
		startGame.draw(menuXPos, startGameYPos);
		settings.draw(menuXPos, settingsYPos);
		highscore.draw(menuXPos, highscoreYPos);
		exit.draw(menuXPos, exitYPos);
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
		
		if(input.isKeyPressed(Input.KEY_ENTER)||input.isKeyPressed(Input.KEY_SPACE)) {
			if(hoverValue == 0) {
				sbg.enterState(States.LEVELSVIEW.getID(), new FadeOutTransition(), new FadeInTransition());
			}
			else if(hoverValue == 1) {
				sbg.enterState(States.SETTINGSVIEW.getID(), new FadeOutTransition(), new FadeInTransition());
			}
			else if(hoverValue == 2) {
				sbg.enterState(States.HIGHSCOREVIEW.getID(), new FadeOutTransition(), new FadeInTransition());
			}
			else if(hoverValue == 3) {
				gc.exit();
			}
			
			hoverValue = 0;
		}
	}
	
	public void moveMenuFocus() {
		switch(hoverValue) {
		case 0:
			hoverYPos = startGameYPos;
			break;
		case 1:
			hoverYPos = settingsYPos;
			break;
		case 2:
			hoverYPos = highscoreYPos;
			break;
		case 3:
			hoverYPos = exitYPos;
			break;
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
