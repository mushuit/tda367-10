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

import tetrix.sound.SoundEffects;
import tetrix.util.Util;
import tetrix.view.StateHandler.States;
import tetrix.view.theme.ThemeHandler;

/**
 * A class responsible for showing the pause menu. The background image is a
 * copy of the GameplayView
 * 
 * @author Linus Karlsson
 * 
 */
public class PausedGameView extends BasicGameState implements IMultipleChoices {

	private int stateID;

	private Image background;
	private Image pauseBox;
	private Image resume;
	private Image newGame;
	private Image mainMenu;
	private Image quit;
	private Image hover;

	private int pauseBoxXPos;
	private int hoverYPos;
	private int hoverValue;

	private enum Choices {
		RESUME(0, 205), NEWGAME(1, 255), MAINMENU(2, 305), QUIT(3, 355);

		private final int id;
		private final int yPos;

		Choices(int id, int yPos) {
			this.id = id;
			this.yPos = yPos;
		}

		private int id() {
			return id;
		}

		private int yPos() {
			return yPos;
		}
	}

	public PausedGameView(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		background = ((GameplayView) sbg.getState(States.GAMEPLAYVIEW.getID()))
				.getPausedScreen();
		hover = ThemeHandler.get(ThemeHandler.HOVER_SMALL_IMG);
		pauseBox = ThemeHandler.get(ThemeHandler.POPUP_IMG);
		resume = ThemeHandler.get(ThemeHandler.RESUME_IMG);
		newGame = ThemeHandler.get(ThemeHandler.NEW_GAME_IMG);
		mainMenu = ThemeHandler.get(ThemeHandler.MAIN_MENU_IMG);
		quit = ThemeHandler.get(ThemeHandler.QUIT_IMG);

		pauseBoxXPos = (Util.WINDOW_WIDTH / 2 - pauseBox.getWidth() / 2);
	}

	public void enter(GameContainer gc, StateBasedGame sgb) {
		hoverValue = Choices.RESUME.id();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw(0, 0);
		pauseBox.draw(pauseBoxXPos,
				(Util.WINDOW_HEIGHT / 2 - pauseBox.getHeight() / 2));
		hover.draw(pauseBoxXPos, hoverYPos);
		resume.draw(pauseBoxXPos, Choices.RESUME.yPos());
		newGame.draw(pauseBoxXPos, Choices.NEWGAME.yPos());
		mainMenu.draw(pauseBoxXPos, Choices.MAINMENU.yPos());
		quit.draw(pauseBoxXPos, Choices.QUIT.yPos());
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int rate)
			throws SlickException {
		Input input = gc.getInput();

		if (input.isKeyPressed(Input.KEY_DOWN)) {
			hoverValue = (hoverValue + 1) % Choices.values().length;
			SoundEffects.instance().menuClickPlay();
		} else if (input.isKeyPressed(Input.KEY_UP)) {
			hoverValue--;
			if (hoverValue < 0) {
				hoverValue = Choices.values().length - 1;
			}
			SoundEffects.instance().menuClickPlay();
		}

		moveMenuFocus();

		if (input.isKeyPressed(Input.KEY_ENTER)) {
			if (hoverValue == Choices.RESUME.id()) {
				input.clearKeyPressedRecord();
				sbg.enterState(States.GAMEPLAYVIEW.getID());
			} else if (hoverValue == Choices.NEWGAME.id()) {
				input.clearKeyPressedRecord();
				((GameplayView) sbg.getState(States.GAMEPLAYVIEW.getID()))
						.newGame();
				sbg.enterState(States.GAMEPLAYVIEW.getID(),
						new FadeOutTransition(), new FadeInTransition());
			} else if (hoverValue == Choices.MAINMENU.id()) {
				input.clearKeyPressedRecord();
				((GameplayView) sbg.getState(States.GAMEPLAYVIEW.getID()))
						.newGame();
				sbg.enterState(States.MAINMENUVIEW.getID(),
						new FadeOutTransition(), new FadeInTransition());
			} else if (hoverValue == Choices.QUIT.id()) {
				gc.exit();
			}
		}

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			input.clearKeyPressedRecord();
			sbg.enterState(States.GAMEPLAYVIEW.getID());
		}
	}

	@Override
	public void moveMenuFocus() {
		for (Choices c : Choices.values()) {
			if (c.id() == hoverValue) {
				hoverYPos = c.yPos();
			}
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
