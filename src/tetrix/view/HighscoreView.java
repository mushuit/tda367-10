package tetrix.view;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tetrix.core.Entry;
import tetrix.core.HighScore;
import tetrix.core.Player;
import tetrix.util.Util;
import tetrix.view.StateHandler.States;
import tetrix.view.theme.ThemeHandler;

/**
 * Class responsible for viewing the high scores.
 * @author Linus Karlsson and Andreas Karlberg
 *
 */
public class HighscoreView extends BasicGameState {

	private int stateID;

	private HighScore highScore;
	private UnicodeFont highScoreDisplay;
	private UnicodeFont numberOnHighScoreDisplay;
	private UnicodeFont nameOnHighScoreDisplay; 
	private UnicodeFont pointsOnHighScoreDisplay;
	private Image background;
	private Image backButton;
	private Image backHover;

	public HighscoreView(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		background = ThemeHandler.get(ThemeHandler.BACKGROUND_IMG);
		backButton = ThemeHandler.get(ThemeHandler.BACK_IMG);
		backHover = ThemeHandler.get(ThemeHandler.HOVER_IMG);
		try {
			highScore = HighScore.instance();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Font font = new Font("Verdana", Font.BOLD, 0);
		highScoreDisplay = new UnicodeFont(font);
		highScoreDisplay = new UnicodeFont(font , 20, true, false);
		highScoreDisplay.addAsciiGlyphs();
		highScoreDisplay.addGlyphs(400, 600);
		highScoreDisplay.getEffects().add(new ColorEffect(java.awt.Color.YELLOW));
		try {
			highScoreDisplay.loadGlyphs();
		} catch (SlickException e1) {
			e1.printStackTrace();
		}

		numberOnHighScoreDisplay = highScoreDisplay;
		nameOnHighScoreDisplay = highScoreDisplay;
		pointsOnHighScoreDisplay = highScoreDisplay;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		Input input = gc.getInput();
		background.draw(0, 0);
		int buttonXPos = Util.WINDOW_WIDTH/2 - backButton.getWidth()/2;
		backHover.draw(buttonXPos, 450);
		backButton.draw(buttonXPos, 450);

		int yPos = 200;
		int index = 1;
		try {
			for(Entry Entry : highScore.getHighScore()) {
				numberOnHighScoreDisplay.drawString(90, yPos, index + ". ");
				nameOnHighScoreDisplay.drawString(115, yPos,"   " +  Entry.getName() + "  ");
				pointsOnHighScoreDisplay.drawString(260, yPos, "  " + Entry.getPoints());
				index++;
				yPos += 20;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(input.isKeyPressed(Input.KEY_ENTER)) {
			sbg.enterState(States.MAINMENUVIEW.getID());
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int rate)
			throws SlickException {
		// Nothing to do here
	}		 

	@Override
	public int getID() {
		return stateID;
	}

}
