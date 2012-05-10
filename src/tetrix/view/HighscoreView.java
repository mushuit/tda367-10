package tetrix.view;

import java.awt.Font;
import java.io.FileNotFoundException;
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
import tetrix.core.HighScore2;
import tetrix.core.Player;
import tetrix.util.Util;
import tetrix.util.theme.ThemeHandler;
import tetrix.view.StateHandler.States;

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
		highScore = HighScore.instance();

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
		
		HighScore2 h;
		List<Entry> es = null;
		try {
			h = new HighScore2();
			es = h.getHighScore();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Entry first = es.get(0);
		Entry second = es.get(1);
		Entry third = es.get(2);
		Entry fourth = es.get(3);
		Entry fifth = es.get(4);
		Entry sixth = es.get(5);
		Entry seventh = es.get(6);
		Entry eight = es.get(7);
		Entry ninth = es.get(8);
		Entry tenth = es.get(9);
		
		Player p1 = new Player(first.getPoints(), first.getName());
		Player p2 = new Player(second.getPoints(), second.getName());
		Player p3 = new Player(third.getPoints(), third.getName());
		Player p4 = new Player(fourth.getPoints(), fourth.getName());
		Player p5 = new Player(fifth.getPoints(), fifth.getName());
		Player p6 = new Player(sixth.getPoints(), sixth.getName());
		Player p7 = new Player(seventh.getPoints(), seventh.getName());
		Player p8 = new Player(eight.getPoints(), eight.getName());
		Player p9 = new Player(ninth.getPoints(), ninth.getName());
		Player p10 = new Player(tenth.getPoints(), tenth.getName());
		
		highScore.addToHighScore(p1);
		highScore.addToHighScore(p2);
		highScore.addToHighScore(p3);
		highScore.addToHighScore(p4);
		highScore.addToHighScore(p5);
		highScore.addToHighScore(p6);
		highScore.addToHighScore(p7);
		highScore.addToHighScore(p8);
		highScore.addToHighScore(p9);
		highScore.addToHighScore(p10);
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
		for(Player p : highScore.getList()) {
			numberOnHighScoreDisplay.drawString(100, yPos, index + ". ");
			nameOnHighScoreDisplay.drawString(125, yPos,"   " +  p.getName() + "  ");
			pointsOnHighScoreDisplay.drawString(240, yPos, "  " + p.getScore());
			index++;
			yPos += 20;
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
