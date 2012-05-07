package tetrix.core;

import java.awt.Font;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.TextField;

public class TestTextInput extends BasicGame {

	private TextField textField;
	private UnicodeFont inputFont;
	
	public TestTextInput(String title) {
		super("TestTextInput");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		textField.render(gc, g);
		textField.setFocus(true);		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		Font font = new Font("Verdana", Font.BOLD, 0);
		inputFont = new UnicodeFont(font);
		inputFont = new UnicodeFont(font , 20, true, false);
		inputFont.addAsciiGlyphs();
		inputFont.addGlyphs(400, 600);
		inputFont.getEffects().add(new ColorEffect(java.awt.Color.YELLOW));
		
		 textField = new TextField(gc, inputFont, 100, 100, 100, 100);
	     textField.setBackgroundColor(Color.blue);
	     textField.setBorderColor(Color.yellow);
	     textField.setTextColor(Color.white);
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		AppGameContainer app = new AppGameContainer( new SlickBasicGame() );
	 
	         app.setDisplayMode(800, 600, false);
	         app.start();
	}

}
