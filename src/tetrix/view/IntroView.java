package tetrix.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;

import tetrix.Main.States;


/**
 * Class responsible for the intro sequence where the user is expected to "press start".
 * @author Linus Karlsson
 *
 */
public class IntroView extends BasicGameState{

	private int stateID;
	
	private Image background = null;
	private Image pressStart = null;
	private Image pressStartMouseOver = null;
	private Image exitGame = null;
	private Image exitGameMouseOver = null;
	
	private int pressStartXpos = 82;
	private int pressStartYpos = 300;
	private int exitGameXpos = 193;
	private int exitGameYpos = 375;
	 
	private boolean inPressStartArea = false;
	private boolean inExitGameArea = false;
	
	public IntroView(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		background = new Image("img/introbackg.png");
		pressStart = new Image("img/startGame.png");
		pressStartMouseOver = new Image("img/startGameMouseOver.png");
		exitGame = new Image("img/exit.png");
		exitGameMouseOver = new Image("img/exitMouseOver.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics arg2)
			throws SlickException {
		background.draw(0, 0);
		pressStart.draw(pressStartXpos, pressStartYpos);
		exitGame.draw(exitGameXpos, exitGameYpos);
		if(inPressStartArea){
			pressStartMouseOver.draw(pressStartXpos, pressStartYpos);
		} else {
			pressStart.draw(pressStartXpos, pressStartYpos);
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
		 
		if( ( mouseX >= pressStartXpos && mouseX <= pressStartXpos + pressStart.getWidth()) &&
		    ( mouseY >= pressStartYpos && mouseY <= pressStartYpos + pressStart.getHeight()) ){
			inPressStartArea = true;
		} else{
			inPressStartArea = false;
		}
		if ( (mouseX >= exitGameXpos && mouseX <= exitGameXpos + exitGame.getWidth()) &&
			    ( mouseY >= exitGameYpos && mouseY <= exitGameYpos + exitGame.getHeight())){
					inExitGameArea = true;
		} else {
			inExitGameArea = false;
		}
		
		if(inPressStartArea){
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				sbg.enterState(States.MAINMENUVIEW.getID());
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
