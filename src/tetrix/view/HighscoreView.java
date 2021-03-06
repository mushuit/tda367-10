package tetrix.view;

import java.awt.Font;
import java.io.FileNotFoundException;

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

	private UnicodeFont highScoreDisplay;
	private UnicodeFont numberOnHighScoreDisplay;
	private UnicodeFont nameOnHighScoreDisplay; 
	private UnicodeFont pointsOnHighScoreDisplay;
	private Image background;
	private Image backButton;
	private Image backHover; 
	private Image highscore;

	public HighscoreView(int stateID) {
		this.stateID = stateID;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		background = ThemeHandler.get(ThemeHandler.BACKGROUND_IMG);
		backButton = ThemeHandler.get(ThemeHandler.BACK_IMG);
		backHover = ThemeHandler.get(ThemeHandler.HOVER_IMG);
		highscore = ThemeHandler.get(ThemeHandler.HIGHSCORE_IMG);

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
		background.draw(0, 0);
		highscore.draw(Util.WINDOW_WIDTH/2 - highscore.getWidth()/2, 100);
		int buttonXPos = Util.WINDOW_WIDTH/2 - backButton.getWidth()/2;
		backHover.draw(buttonXPos, 450);
		backButton.draw(buttonXPos, 450);

		int yPos = 200;
		int index = 1;
		try {
			for(Entry Entry : HighScore.instance().getHighScore()) {
				numberOnHighScoreDisplay.drawString(90, yPos, index + ". ");
				nameOnHighScoreDisplay.drawString(115, yPos,"   " +  Entry.getName() + "  ");
				pointsOnHighScoreDisplay.drawString(260, yPos, "  " + Entry.getPoints());
				index++;
				yPos += 20;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int rate)
			throws SlickException {
		Input input = gc.getInput();
		
		if(input.isKeyPressed(Input.KEY_ENTER)) {
			sbg.enterState(States.MAINMENUVIEW.getID());
		}
	}		 

	@Override
	public int getID() {
		return stateID;
	}

}
