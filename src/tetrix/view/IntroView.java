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
	
	private Image background;
	private Image pressStart;
	private Image pressStartMouseOver;
	
	
	private int pressStartXpos = 82;
	private int pressStartYpos = 300;
	 
	private boolean inPressStartArea = false;
	
	public IntroView(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		background = new Image("img/introbackg.png");
		pressStart = new Image("img/startGame.png");
		pressStartMouseOver = new Image("img/startGameMouseOver.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics arg2)
			throws SlickException {
		background.draw(0, 0);
		pressStart.draw(pressStartXpos, pressStartYpos);
		if(inPressStartArea){
			pressStartMouseOver.draw(pressStartXpos, pressStartYpos);
		} else {
			pressStart.draw(pressStartXpos, pressStartYpos);
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
		
		if(inPressStartArea){
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				sbg.enterState(States.MAINMENUVIEW.getID());
			}
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
