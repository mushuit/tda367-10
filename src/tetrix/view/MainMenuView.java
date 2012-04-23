package tetrix.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tetrix.Main.States;

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
	
	private int startGameXpos;
	private int startGameYpos;
	private int exitGameXpos;
	private int exitGameYpos;
	 
	private boolean inStartGameArea = false;
	private boolean inExitGameArea = false;
	
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
		exitGame = new Image("img/exit.png");
		exitGameMouseOver = new Image("img/exitMouseOver.png");
		exitGameXpos = 200-(exitGame.getWidth()/2);
		exitGameYpos = 375;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics arg2)
			throws SlickException {
		background.draw(0,0);
		startGame.draw(startGameXpos, startGameYpos);
		exitGame.draw(exitGameXpos, exitGameYpos);
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
		
		if(inStartGameArea){
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				sbg.enterState(States.GAMEPLAYVIEW.getID());
			}
		} else if (inExitGameArea){
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				gc.exit();
			}
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
