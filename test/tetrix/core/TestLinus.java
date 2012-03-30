package tetrix.core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


import tetrix.HighscoreView;
import tetrix.core.Util;


public class TestLinus extends StateBasedGame {

	public enum States {
		HIGHSCOREVIEW(4);
		
		private final int stateID;  
		  
		States(int stateID) {  
			this.stateID = stateID;  
		} 
		
		public int getID() {
		    return stateID;
		  }
	}
	
	public TestLinus() {
		super("Tetrix");
        this.addState(new HighscoreView(States.HIGHSCOREVIEW.getID()));
        
        // TODO CHANGE TO INTROVIEW WHEN TESTING IS DONE
        this.enterState(States.HIGHSCOREVIEW.getID());
	}

	@Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.getState(States.HIGHSCOREVIEW.getID()).init(gameContainer, this);
    }
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new TestLinus());
		// Set FPS to 60
		app.setTargetFrameRate(Util.FPS);
        app.setDisplayMode(Util.WINDOW_WIDTH, Util.WINDOW_HEIGHT, false);
        app.start();
	}	
}