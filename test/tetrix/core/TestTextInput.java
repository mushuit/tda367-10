package tetrix.core;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.TextField;

@SuppressWarnings("deprecation")
public class TestTextInput extends BasicGame {

	private TextField textField;
	private TrueTypeFont inputFont;
	private TrueTypeFont inputDescFont;
	private String message;
	private static final int windowHeight = 600;
	private static final int windowWidth = 400;
	private Shape messageDialog;
	
	private int dialogWidth = 210;
	private int dialogHeight = 100;
	
	/**
	 * 63
	 */

	public TestTextInput() {
		super("TestTextInput");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		//g.fill(messageDialog);
		inputDescFont.drawString((windowWidth/2 - dialogWidth/2) + 10, (windowHeight/2 - dialogHeight/2) + 10, "Congratz! Enter your name", Color.green);
		textField.render(gc, g);
		textField.setFocus(true);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		Font font = new Font("Verdana", Font.BOLD, 20);
		Font descriptionFont = new Font("Verdana", Font.BOLD, 12);
		inputFont = new TrueTypeFont(font, true);
		inputDescFont = new TrueTypeFont(descriptionFont, true);
		
		//messageDialog = new Rectangle(windowWidth / 2
			//	- dialogWidth / 2, windowHeight / 2 - dialogHeight / 2,
				//dialogWidth, dialogHeight);
		
		int textFieldWidth = 200;
		int textFieldHeight = 30;
		textField = new TextField(gc, inputFont, windowWidth / 2
				- textFieldWidth / 2, (windowHeight / 2 - textFieldHeight / 2),
				textFieldWidth, textFieldHeight);
		textField.setBackgroundColor(Color.white);
		textField.setBorderColor(Color.pink);
		textField.setTextColor(Color.black);
	}

	@Override
	public void update(GameContainer gc, int rate) throws SlickException {
		Input input = gc.getInput();
		
		if(input.isKeyPressed(Input.KEY_ENTER)) {
			message = textField.getText();
			FileReader p;
			try {
				p = new FileReader("highscore/playername.dat");
				List<String> ps = new ArrayList<String>();
				ps.add(message);
				p.writePName(ps);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new TestTextInput());
		app.setDisplayMode(windowWidth, windowHeight, false);
		app.start();
	}

}
