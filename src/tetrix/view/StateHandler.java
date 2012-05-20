package tetrix.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Class managing the loading of the different states.
 * 
 * @author Linus Karlsson
 * 
 */
public class StateHandler extends StateBasedGame {

	public enum States {
		INTROVIEW(1),
		MAINMENUVIEW(2), 
		LEVELSVIEW(3), 
		GAMEPLAYVIEW(4), 
		SETTINGSVIEW(5), 
		HIGHSCOREVIEW(6), 
		PAUSEDGAMEVIEW(7),
		GAMEOVERVIEW(8);

		private final int stateID;

		States(int stateID) {
			this.stateID = stateID;
		}

		public int getID() {
			return stateID;
		}
	}

	public StateHandler() {
		super("Tetrix");
	}

	@Override
	public void initStatesList(GameContainer gameContainer)
			throws SlickException {
		this.addState(new IntroView(States.INTROVIEW.getID()));
		
		
		Music music = new Music("Sound/background-music.wav");
		music.loop();
		
	}

	public static void addStates(StateBasedGame sbg) {
		sbg.addState(new MainMenuView(States.MAINMENUVIEW.getID()));
		sbg.addState(new LevelsView(States.LEVELSVIEW.getID()));
		sbg.addState(new GameplayView(States.GAMEPLAYVIEW.getID()));
		sbg.addState(new SettingsView(States.SETTINGSVIEW.getID()));
		sbg.addState(new HighscoreView(States.HIGHSCOREVIEW.getID()));
		sbg.addState(new PausedGameView(States.PAUSEDGAMEVIEW.getID()));
		sbg.addState(new GameOverView(States.GAMEOVERVIEW.getID()));
	}
	
	public static void initStates(GameContainer gc, StateBasedGame sbg) throws SlickException {
		sbg.getState(States.MAINMENUVIEW.getID()).init(gc, sbg);
		sbg.getState(States.LEVELSVIEW.getID()).init(gc, sbg);
		sbg.getState(States.GAMEPLAYVIEW.getID()).init(gc, sbg);
		sbg.getState(States.SETTINGSVIEW.getID()).init(gc, sbg);
		sbg.getState(States.HIGHSCOREVIEW.getID()).init(gc, sbg);
		sbg.getState(States.PAUSEDGAMEVIEW.getID()).init(gc, sbg);
		sbg.getState(States.GAMEOVERVIEW.getID()).init(gc, sbg);
	}
}
