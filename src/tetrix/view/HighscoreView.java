package tetrix.view;

import java.awt.Font;

import javax.swing.JTable;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tetrix.core.HighScore;
import tetrix.core.Player;

/**
 * Class responsible for viewing the high scores.
 * @author Linus Karlsson
 *
 */
public class HighscoreView extends BasicGameState {

	private int stateID;
	
	private HighScore highScore;
	private UnicodeFont highScoreDisplay;
	private Image background;
	
	public HighscoreView(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		background= new Image("img/game_background.png");
		highScore = HighScore.instance();
		
		Font font = new Font("Verdana", Font.BOLD, 20);
		highScoreDisplay = new UnicodeFont(font);
		highScoreDisplay = new UnicodeFont(font , 15, true, false);
		highScoreDisplay.addAsciiGlyphs();
		highScoreDisplay.addGlyphs(400, 600);
		highScoreDisplay.getEffects().add(new ColorEffect(java.awt.Color.MAGENTA));
		try {
			highScoreDisplay.loadGlyphs();
		} catch (SlickException e1) {
			e1.printStackTrace();
		}
		
		Player p1 = new Player(1000, "Erik");
		Player p2 = new Player(434, "Johan");
		Player p3 = new Player(4563, "Albin");
		Player p4 = new Player(6745, "Oskar");
		Player p5 = new Player(2342, "Tomas");
		Player p6 = new Player(6436, "Jesper");
		Player p7 = new Player(1231, "Marcus");
		Player p8 = new Player(2342, "Tomas");
		Player p9 = new Player(6436, "Jesper");
		
		highScore.addToHighScore(p1);
		highScore.addToHighScore(p2);
		highScore.addToHighScore(p3);
		highScore.addToHighScore(p4);
		highScore.addToHighScore(p5);
		highScore.addToHighScore(p6);
		highScore.addToHighScore(p7);
		highScore.addToHighScore(p8);
		highScore.addToHighScore(p9);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		background.draw(0, 0);

		int yPos = 200;
		int index = 1;
		for(Player p : highScore.getList()) {
			highScoreDisplay.drawString(100, yPos, index + ". " + p.getName() + "     " 
					+ p.getScore());
			index++;
			yPos += 20;
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
	}		 
	
	@Override
	public int getID() {
		return stateID;
	}

}
