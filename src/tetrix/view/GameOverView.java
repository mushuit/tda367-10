package tetrix.view;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import tetrix.core.HighScore;
import tetrix.core.Player;
import tetrix.util.Util;
import tetrix.view.StateHandler.States;
import tetrix.view.theme.ThemeHandler;

/**
 * The game over view that is displayed when the tetrominoes has reached the top of the window.
 * @author Linus Karlsson
 *
 */
public class GameOverView extends BasicGameState implements IMultipleChoices {

	private int stateID;

	private Image background;
	private Image gameOver;
	private Image hover;
	private Image newGame;
	private Image mainMenu;
	private Image highscore;
	private UnicodeFont resultText; 
	private UnicodeFont highscoreText; 

	private int xPos;
	private int hoverYPos;
	private int newGameYPos;
	private int mainMenuYPos;
	private int highscoreYPos;

	private int hoverValue;

	public GameOverView(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		background = ThemeHandler.get(ThemeHandler.GAMEOVER_BACKGROUND);
		gameOver = ThemeHandler.get(ThemeHandler.GAMEOVER_LOGO);
		hover = ThemeHandler.get(ThemeHandler.GAMEOVER_HOVER);
		newGame = ThemeHandler.get(ThemeHandler.NEW_GAME_BIG);
		mainMenu = ThemeHandler.get(ThemeHandler.MAIN_MENU_BIG);
		highscore = ThemeHandler.get(ThemeHandler.HIGHSCORE_IMG);

		xPos = Util.WINDOW_WIDTH / 2 - newGame.getWidth() / 2;
		newGameYPos = 290;
		mainMenuYPos = 360;
		highscoreYPos = 430;
		hoverYPos = newGameYPos;

		hoverValue = 0;
		
		Font font = new Font("Verdana", Font.BOLD, 0);
		resultText = new UnicodeFont(font , 20, true, false);
		highscoreText = new UnicodeFont(font , 16, true, false);
		initText(resultText);
		initText(highscoreText);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		background.draw(0, 0);
		gameOver.draw(0, 100);

		hover.draw(xPos, hoverYPos);
		newGame.draw(xPos, newGameYPos);
		mainMenu.draw(xPos, mainMenuYPos);
		highscore.draw(xPos, highscoreYPos);
		
		resultText.drawString(110, 180, "You got " + Player.getScore() + " points");
		
		if(HighScore.writtenToHighscore()) {
			highscoreText.drawString(50, 210, "You made it to the highscore list!", Color.green);
		} else {
			highscoreText.drawString(40, 210, "You did not reach the highscore list", Color.red);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int value)
			throws SlickException {
		Input input = gc.getInput();

		if (input.isKeyPressed(Input.KEY_DOWN)) {
			hoverValue = (hoverValue + 1) % 3;
		} else if (input.isKeyPressed(Input.KEY_UP)) {
			hoverValue--;
			if (hoverValue < 0) {
				hoverValue = 2;
			}
		}

		moveMenuFocus();

		if (input.isKeyPressed(Input.KEY_ENTER)||input.isKeyPressed(Input.KEY_SPACE)) {
			if (hoverValue == 0) {
				((GameplayView) sbg.getState(States.GAMEPLAYVIEW.getID())).newGame();
				sbg.enterState(States.GAMEPLAYVIEW.getID(),
						new FadeOutTransition(), new FadeInTransition());
			} else if (hoverValue == 1) {
				((GameplayView) sbg.getState(States.GAMEPLAYVIEW.getID())).newGame();
				sbg.enterState(States.MAINMENUVIEW.getID(),
						new FadeOutTransition(), new FadeInTransition());
			} else if (hoverValue == 2) {
				sbg.enterState(States.HIGHSCOREVIEW.getID(),
						new FadeOutTransition(), new FadeInTransition());
			}
		}
	}
	
	public void initText(UnicodeFont font) {
		font.addAsciiGlyphs();
		font.addGlyphs(400, 600);
		font.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
		try {
			font.loadGlyphs();
		} catch (SlickException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

	@Override
	public void moveMenuFocus() {
		switch (hoverValue) {
		case 0:
			hoverYPos = newGameYPos;
			break;
		case 1:
			hoverYPos = mainMenuYPos;
			break;
		case 2:
			hoverYPos = highscoreYPos;
			break;
		}
	}

}
