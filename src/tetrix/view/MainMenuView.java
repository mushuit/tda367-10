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
 * Class responsible for the main menu view.
 * 
 * @author Linus Karlsson
 * 
 */
public class MainMenuView extends BasicGameState implements IMultipleChoices {

	private int stateID;

	private Image background;
	private Image tetrixLogo;
	private Image startGame;
	private Image exit;
	private Image settings;
	private Image highscore;
	private Image menuHover;

	private int menuXPos;
	private int hoverYPos;
	private int hoverValue;

	private enum Choices {
		STARTGAME(0, 250), SETTINGS(1, 320), HIGHSCORE(2, 390), EXIT(3, 460);

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

		menuXPos = (Util.WINDOW_WIDTH / 2) - (startGame.getWidth() / 2);
	}

	public void enter(GameContainer gc, StateBasedGame sbg) {
		hoverValue = Choices.STARTGAME.id();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw(0, 0);
		menuHover.draw(menuXPos, hoverYPos);
		tetrixLogo.draw(Util.WINDOW_WIDTH / 2 - (tetrixLogo.getWidth() / 2),
				100);
		startGame.draw(menuXPos, Choices.STARTGAME.yPos());
		settings.draw(menuXPos, Choices.SETTINGS.yPos());
		highscore.draw(menuXPos, Choices.HIGHSCORE.yPos());
		exit.draw(menuXPos, Choices.EXIT.yPos());
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
			if (hoverValue == Choices.STARTGAME.id()) {
				sbg.enterState(States.LEVELSVIEW.getID(),
						new FadeOutTransition(), new FadeInTransition());
			} else if (hoverValue == Choices.SETTINGS.id()) {
				sbg.enterState(States.SETTINGSVIEW.getID(),
						new FadeOutTransition(), new FadeInTransition());
			} else if (hoverValue == Choices.HIGHSCORE.id()) {
				sbg.enterState(States.HIGHSCOREVIEW.getID(),
						new FadeOutTransition(), new FadeInTransition());
			} else if (hoverValue == Choices.EXIT.id()) {
				gc.exit();
			}
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

	@Override
	public void moveMenuFocus() {
		for (Choices c : Choices.values()) {
			if (c.id() == hoverValue) {
				hoverYPos = c.yPos();
			}
		}
	}
}
