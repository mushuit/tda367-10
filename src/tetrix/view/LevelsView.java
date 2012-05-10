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

import tetrix.util.Util;
import tetrix.view.StateHandler.States;

/**
 * Class responsible for the view before the game starts where the user has to choose a level.
 * @author Linus Karlsson
 *
 */
public class LevelsView extends BasicGameState{

	private int stateID;
	
	private Image background;
	private Image hover;
	private Image easyButton;
	private Image hardButton;
	
	private int xPos;
	private int easyYPos;
	private int hardYPos;
	private int hoverYPos;
	
	private int hoverValue;
	
	public LevelsView(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		background = new Image("img/background.png");
		hover = new Image("img/menu_hover.png");
		easyButton = new Image("img/easy.png");
		hardButton = new Image("img/hard.png");
		
		hoverValue = 0;
		easyYPos = 300;
		hardYPos = 400;
		hoverYPos = easyYPos;
		xPos = Util.WINDOW_WIDTH/2 - hover.getWidth()/2;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		background.draw(0,0);
		
		hover.draw(xPos, hoverYPos);
		easyButton.draw(xPos, easyYPos);
		hardButton.draw(xPos, hardYPos);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		Input input = gc.getInput();
		
		if(input.isKeyPressed(Input.KEY_DOWN)) {
			hoverValue = (hoverValue + 1) % 2;
		} 
		else if(input.isKeyPressed(Input.KEY_UP)) {
			hoverValue--;
			if(hoverValue < 0) {
				hoverValue = 1;
			}
		}
		
		moveMenuFocus();
		
		if(input.isKeyPressed(Input.KEY_ENTER)) {
			if(hoverValue == 0) {
			
			}
			else if(hoverValue == 1) {
				
			}
		}
		
		sbg.enterState(States.GAMEPLAYVIEW.getID(), new FadeOutTransition(), new FadeInTransition());
		((GameplayView) sbg.getState(States.GAMEPLAYVIEW.getID())).startTimer(); 
	}
		
	public void moveMenuFocus() {
		switch(hoverValue) {
		case 0:
			hoverYPos = easyYPos;
			break;
		case 1:
			hoverYPos = hardYPos;
			break;
		}
	}


	@Override
	public int getID() {
		return stateID;
	}

}
