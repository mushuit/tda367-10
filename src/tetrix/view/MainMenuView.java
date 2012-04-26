package tetrix.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tetrix.view.StateHandler.States;


/**
 * Class responsible for the view before the game starts where the user has to choose difficulty.
 * @author Linus Karlsson
 *
 */
public class MainMenuView extends BasicGameState{

	private int stateID;
	private Image background;
	private Image startGame;
	private Image startGameMouseOver;
	private Image exitGame;
	private Image exitGameMouseOver;
	private Image settings;
	private Image settingsMouseOver;
	private Image highScore; 			//mouseover too
	
	
	private int startGameXpos;
	private int startGameYpos;
	private int exitGameXpos;
	private int exitGameYpos;
	private int settingsXpos;
	private int settingsYpos;
	private int highScoreXpos;
	private int highScoreYpos;
	 
	private boolean inStartGameArea = false;
	private boolean inExitGameArea = false;
	private boolean inSettingsArea = false;
	private boolean inHighScoreArea = false;
	
	public MainMenuView(int stateID) {
        this.stateID = stateID;
    }
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		background = new Image("img/menubackg.png");
		startGame = new Image("img/startGame.png");
		startGameMouseOver = new Image("img/startGameMouseOver.png");
		startGameXpos = 200-(startGame.getWidth()/2);
		startGameYpos = 300;
		settings = new Image("img/settings.png");
		settingsMouseOver = new Image("img/settingsMouseOver.png");
		settingsXpos = 200-(settings.getWidth()/2);
		settingsYpos = 350;
		exitGame = new Image("img/exit.png");
		exitGameMouseOver = new Image("img/exitMouseOver.png");
		exitGameXpos = 200-(exitGame.getWidth()/2);
		exitGameYpos = 425;
		
		highScore = new Image("img/highScore.png");
		
		highScoreXpos = 200-(highScore.getWidth()/2);
		highScoreYpos = 390;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics arg2)
			throws SlickException {
		background.draw(0,0);
		startGame.draw(startGameXpos, startGameYpos);
		exitGame.draw(exitGameXpos, exitGameYpos);
		settings.draw(settingsXpos, settingsYpos);
		highScore.draw(highScoreXpos, highScoreYpos);
		if(inStartGameArea){
			startGameMouseOver.draw(startGameXpos, startGameYpos);
		} else {
			startGame.draw(startGameXpos, startGameYpos);
		}

		if(inExitGameArea){
			exitGameMouseOver.draw(exitGameXpos, exitGameYpos);
		} else {
			exitGame.draw(exitGameXpos, exitGameYpos);
		}
		
		if(inSettingsArea){
			settingsMouseOver.draw(settingsXpos, settingsYpos);
		} else {
			settings.draw(settingsXpos, settingsYpos);
		}
//		if(inHighScoreArea){
//			highScoreMouseOver.draw(highScoreXpos, highScoreYpos);
//		} else {
//			highScore.draw(highScoreXpos, highScoreYpos);
//		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		
		Input input = gc.getInput();
		 
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		 
		if( ( mouseX >= startGameXpos && mouseX <= startGameXpos + startGame.getWidth()) &&
		    ( mouseY >= startGameYpos && mouseY <= startGameYpos + startGame.getHeight()) ){
			inStartGameArea = true;
		} else{
			inStartGameArea = false;
		}
		if ( (mouseX >= exitGameXpos && mouseX <= exitGameXpos + exitGame.getWidth()) &&
			    ( mouseY >= exitGameYpos && mouseY <= exitGameYpos + exitGame.getHeight())){
					inExitGameArea = true;
		} else {
			inExitGameArea = false;
		}
		if( ( mouseX >= settingsXpos && mouseX <= settingsXpos + settings.getWidth()) &&
			    ( mouseY >= settingsYpos && mouseY <= settingsYpos + settings.getHeight()) ){
				inSettingsArea = true;
		} else{
				inSettingsArea = false;
		}
		if( ( mouseX >= highScoreXpos && mouseX <= highScoreXpos + highScore.getWidth()) &&
			    ( mouseY >= highScoreYpos && mouseY <= highScoreYpos + highScore.getHeight()) ){
				inHighScoreArea = true;
		} else{
				inHighScoreArea = false;
		}
		
		if(inStartGameArea){
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) ){
				sbg.enterState(States.GAMEPLAYVIEW.getID());
			}
		} else if(inSettingsArea){
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) ){
				sbg.enterState(States.SETTINGSVIEW.getID());
			} 
		} else if (inExitGameArea){
				if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) ){
				gc.exit();
			}
		} else if(inHighScoreArea){
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) ){
				sbg.enterState(States.HIGHSCOREVIEW.getID());
			} 
		}
		if (input.isKeyPressed(Input.KEY_ENTER) ){
			sbg.enterState(States.GAMEPLAYVIEW.getID());
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
