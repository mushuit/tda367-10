package tetrix.view;

import java.io.FileNotFoundException;

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
import tetrix.sound.GameMusic;
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

	private Image backgroundImage;
	private Image pauseFrame;
	private Image resumeButton;
	private Image newGameButton;
	private Image mainMenuButton;
	private Image quitButton;
	private Image menuHover;

	private int pauseFrameXPos;
	private int menuHoverYPos;
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
		backgroundImage = ((GameplayView) sbg.getState(States.GAMEPLAYVIEW.getID()))
				.getPausedScreen();
		menuHover = ThemeHandler.get(ThemeHandler.HOVER_SMALL_IMG);
		pauseFrame = ThemeHandler.get(ThemeHandler.POPUP_IMG);
		resumeButton = ThemeHandler.get(ThemeHandler.RESUME_IMG);
		newGameButton = ThemeHandler.get(ThemeHandler.NEW_GAME_IMG);
		mainMenuButton = ThemeHandler.get(ThemeHandler.MAIN_MENU_IMG);
		quitButton = ThemeHandler.get(ThemeHandler.QUIT_IMG);

		pauseFrameXPos = (Util.WINDOW_WIDTH / 2 - pauseFrame.getWidth() / 2);
	}

	public void enter(GameContainer gc, StateBasedGame sgb) {
		hoverValue = Choices.RESUME.id();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		backgroundImage.draw(0, 0);
		pauseFrame.draw(pauseFrameXPos,
				(Util.WINDOW_HEIGHT / 2 - pauseFrame.getHeight() / 2));
		menuHover.draw(pauseFrameXPos, menuHoverYPos);
		resumeButton.draw(pauseFrameXPos, Choices.RESUME.yPos());
		newGameButton.draw(pauseFrameXPos, Choices.NEWGAME.yPos());
		mainMenuButton.draw(pauseFrameXPos, Choices.MAINMENU.yPos());
		quitButton.draw(pauseFrameXPos, Choices.QUIT.yPos());
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
				sbg.enterState(States.MAINMENUVIEW.getID());
			} else if (hoverValue == Choices.QUIT.id()) {
				gc.exit();
			}
			
			try {
				GameMusic.instance().gameMusicResume();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
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
				menuHoverYPos = c.yPos();
			}
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
