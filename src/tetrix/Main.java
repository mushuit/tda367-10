package tetrix;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import tetrix.util.Util;
import tetrix.view.GameplayView;
import tetrix.view.HighscoreView;
import tetrix.view.IntroView;
import tetrix.view.LevelsView;
import tetrix.view.MainMenuView;
import tetrix.view.SettingsView;


public class Main extends StateBasedGame {

	public enum States {
		INTROVIEW(-1),
		MAINMENUVIEW(0),
		LEVELSVIEW(1),
		GAMEPLAYVIEW(2),
		SETTINGSVIEW(3),
		HIGHSCOREVIEW(4);
		
		private final int stateID;  
		  
		States(int stateID) {  
			this.stateID = stateID;  
		} 
		
		public int getID() {
		    return stateID;
		  }
	}
	
	public Main() {
		super("Tetrix");
		
		this.addState(new IntroView(States.INTROVIEW.getID()));
		this.addState(new MainMenuView(States.MAINMENUVIEW.getID()));
		this.addState(new LevelsView(States.LEVELSVIEW.getID()));
        this.addState(new GameplayView(States.GAMEPLAYVIEW.getID()));
        this.addState(new SettingsView(States.SETTINGSVIEW.getID()));
        this.addState(new HighscoreView(States.HIGHSCOREVIEW.getID()));
        
        // TODO CHANGE TO INTROVIEW WHEN TESTING IS DONE
        this.enterState(States.INTROVIEW.getID());
	}

	@Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
		this.getState(States.INTROVIEW.getID()).init(gameContainer, this);
        this.getState(States.MAINMENUVIEW.getID()).init(gameContainer, this);
        this.getState(States.GAMEPLAYVIEW.getID()).init(gameContainer, this);
        this.addState(new HighscoreView(States.HIGHSCOREVIEW.getID()));
    }
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main());
		// Set FPS to 60
		app.setTargetFrameRate(Util.FPS);
		app.setShowFPS(false);
        app.setDisplayMode(Util.WINDOW_WIDTH, Util.WINDOW_HEIGHT, false);
        app.start();
	}	
}