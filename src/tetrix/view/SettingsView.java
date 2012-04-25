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
 * Class responsible for viewing different settings for the user to control.
 * @author Linus Karlsson
 *
 */
public class SettingsView extends BasicGameState {

	private int stateID;
	private Image background;
	private Image back;
	private Image backMouseOver;

	private int backXpos;
	private int backYpos;
	 
	private boolean inBackArea = false;
	
	public SettingsView(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		// TODO Auto-generated method stub
		background = new Image("img/settingsbackg.png");
		back = new Image("img/backButton.png");
		backMouseOver = new Image("img/backButtonMouseOver.png");
		backXpos = 200-(back.getWidth()/2);
		backYpos = 414;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		background.draw(0,0);
		back.draw(backXpos, backYpos);
		if(inBackArea){
			backMouseOver.draw(backXpos, backYpos);
		} else {
			back.draw(backXpos, backYpos);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		Input input = gc.getInput();
		 
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		 
		if( ( mouseX >= backXpos && mouseX <= backXpos + back.getWidth()) &&
			( mouseY >= backYpos && mouseY <= backYpos + back.getHeight()) ){
			inBackArea = true;
		} else{
			inBackArea = false;
		}
		if(inBackArea){
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) ){
				sbg.enterState(States.MAINMENUVIEW.getID());
			}
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
