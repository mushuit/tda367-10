package tetrix.view;

//TODO
//NYA BILDER och positioner
//Mellanslag i menyer
//övergångarna
//Ljud i andra klasser i denna kopplade till fxVolume
//musicVolume

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tetrix.core.FileReader;
import tetrix.core.HighScore;
import tetrix.sound.SoundEffects;
import tetrix.util.Util;
import tetrix.view.StateHandler.States;
import tetrix.view.theme.ThemeHandler;

/**
 * Class responsible for viewing different settings for the user to control.
 * 
 * @author Jonathan Kara, Andreas Karlberg, Linus
 * 
 */
public class SettingsView extends BasicGameState implements IMultipleChoices{

	private int stateID;

	private Image tetrixLogo;
	private Image background;
	private Image back;
	private Image effects;
	private Image music;

	private Image fxSlider;
	private Image fxSliderPin;
	private Image musicSlider;
	private Image musicSliderPin;
	private Image menuHover;

	private Image cannon;
	private Image cannon2;
	private Image cannon3;
	private Image cannon4;
	private Image cannon5;

	private Image rightArrow;
	private Image rightArrowHover;
	private Image leftArrow;
	private Image leftArrowHover;

	private int fxSliderPinXPos;
	private int musicSliderPinXPos;
	private float fxVolume;
	private float musicVolume;

	private int cannonYPos;
	private int cannonXPos;
	private int cannonValue;

	private int rightArrowXpos;
	private int leftArrowXpos;

	private Sound fx;
	private int hoverValue;
	private int hoverYPos;
	private int menuXPos;
	private int playerYPos;

	private TextField nameField;
	
	@SuppressWarnings("deprecation")
	private TrueTypeFont inputFont;
	@SuppressWarnings("deprecation")
	private TrueTypeFont inputDescFont;
	
	private String playerName;
	private int dialogWidth = 210;
	private int dialogHeight = 100;

	private boolean rightKeyIsDown;
	private boolean leftKeyIsDown;
	
	private enum Choices {
		FXVOLUME(0, 250), MUSICVOLUME(1, 320), CANNONCHANGER(2, 390), PLAYERNAME(3, 460), BACK(4, 460);

		private final int id;
		private final int yPos;

		Choices(int id, int yPos) {
			this.id = id;
			this.yPos = yPos;
		}

		private int id() {
			return id;
		}

		private int yPos() {
			return yPos;
		}
	}

	public SettingsView(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		background = ThemeHandler.get(ThemeHandler.BACKGROUND_IMG);
		tetrixLogo = ThemeHandler.get(ThemeHandler.TETRIX_LOGO_IMG);
		menuHover = ThemeHandler.get(ThemeHandler.HOVER_IMG);
		back = ThemeHandler.get(ThemeHandler.BACK_IMG);
		effects = ThemeHandler.get(ThemeHandler.EFFECTS_IMG);
		music = ThemeHandler.get(ThemeHandler.MUSIC_IMG);
		
		cannon = ThemeHandler.getBlockOrCannon(ThemeHandler.CANNON_IMG);
		cannon2 = ThemeHandler.getBlockOrCannon(ThemeHandler.CANNON2_IMG);
		cannon3 = ThemeHandler.getBlockOrCannon(ThemeHandler.CANNON3_IMG);
		cannon4 = ThemeHandler.getBlockOrCannon(ThemeHandler.CANNON4_IMG);
		cannon5 = ThemeHandler.getBlockOrCannon(ThemeHandler.CANNON5_IMG);
		
		fxSliderPin = ThemeHandler.get(ThemeHandler.SLIDE_PIN_IMG);
		rightArrow = ThemeHandler.get(ThemeHandler.RIGHT_ARROW_IMG);
		rightArrowHover = ThemeHandler.get(ThemeHandler.RIGHT_ARROW_HOVER_IMG);
		leftArrow = ThemeHandler.get(ThemeHandler.LEFT_ARROW_IMG);
		leftArrowHover = ThemeHandler.get(ThemeHandler.LEFT_ARROW_HOVER_IMG);
		back = ThemeHandler.get(ThemeHandler.BACK_IMG);
		
		fxSliderPinXPos = 0;
		musicSliderPinXPos = 0;
		cannonXPos = 90; // temporary value
		cannonYPos = 390; // change
		cannonValue = 0;

		rightArrowXpos = cannonXPos + cannon.getWidth() + 5;
		leftArrowXpos = cannonXPos - leftArrow.getWidth() - 5;
		menuXPos = (Util.WINDOW_WIDTH / 2) - (menuHover.getWidth() / 2);
		playerYPos = 420; // change

		Font font = new Font("Verdana", Font.BOLD, 20);
		Font descriptionFont = new Font("Verdana", Font.BOLD, 12);
		inputFont = new TrueTypeFont(font, true);
		inputDescFont = new TrueTypeFont(descriptionFont, true);

		int textFieldWidth = 200;
		int textFieldHeight = 30;
		nameField = new TextField(gc, inputFont, Util.WINDOW_WIDTH / 2
				- textFieldWidth / 2, (playerYPos + textFieldHeight / 2),
				textFieldWidth, textFieldHeight);
		nameField.setBackgroundColor(Color.white);
		nameField.setBorderColor(Color.pink);
		nameField.setTextColor(Color.black);

		rightKeyIsDown = false;
		leftKeyIsDown = false;

		try {
			nameField.setText(FileReader.getRow());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) {
		hoverValue = Choices.FXVOLUME.id();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics arg2)
			throws SlickException {
		background.draw(0, 0);
		menuHover.draw(menuXPos, hoverYPos);
		tetrixLogo.draw(Util.WINDOW_WIDTH / 2 - (tetrixLogo.getWidth() / 2),
				100);
		effects.draw(menuXPos, Choices.FXVOLUME.yPos());
		music.draw(menuXPos, Choices.MUSICVOLUME.yPos());

		fxSliderPin.draw(fxSliderPinXPos, Choices.FXVOLUME.yPos());
		musicSliderPin.draw(musicSliderPinXPos, Choices.MUSICVOLUME.yPos());

		if (cannonValue == 0) {
			cannon.draw(cannonXPos, cannonYPos);
		} else if (cannonValue == 1) {
			cannon2.draw(cannonXPos, cannonYPos);
		} else if (cannonValue == 2) {
			cannon3.draw(cannonXPos, cannonYPos);
		} else if (cannonValue == 3) {
			cannon4.draw(cannonXPos, cannonYPos);
		} else if (cannonValue == 4) {
			cannon5.draw(cannonXPos, cannonYPos);
		}

		back.draw(menuXPos, Choices.BACK.yPos());
		;
		if (hoverValue == 0) {
			fxSliderPin.draw(fxSliderPinXPos, Choices.FXVOLUME.yPos());
		}
		if (hoverValue == 1) {
			musicSliderPin.draw(musicSliderPinXPos, Choices.MUSICVOLUME.yPos());
		}

		if (rightKeyIsDown) {
			rightArrowHover.draw(rightArrowXpos, cannonYPos);
		} else if (!rightKeyIsDown) {
			rightArrow.draw(rightArrowXpos, cannonYPos);
		}
		if (leftKeyIsDown) {
			leftArrowHover.draw(leftArrowXpos, cannonYPos);
		} else if (!leftKeyIsDown) {
			leftArrow.draw(leftArrowXpos, cannonYPos);
		}

		arg2.setColor(Color.lightGray);
		inputDescFont.drawString((Util.WINDOW_WIDTH / 2 - dialogWidth / 2) + 50,
				(playerYPos), "Enter your name", Color.green);
		nameField.render(gc, arg2);
		nameField.setFocus(true);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		Input input = gc.getInput();

		if (input.isKeyPressed(Input.KEY_DOWN)) {
			hoverValue = (hoverValue + 1) % 5;
			fx.play(1, SoundEffects.getFxVolume());
		} else if (input.isKeyPressed(Input.KEY_UP)) {
			hoverValue--;
			if (hoverValue < 0) {
				hoverValue = 4;
			}
			fx.play(1, SoundEffects.getFxVolume());
		}

		moveMenuFocus();

		if (hoverValue == 0) {
			if (input.isKeyDown(Input.KEY_LEFT)) {
				if (fxSliderPinXPos > 46) { // FIX
					fxSliderPinXPos = fxSliderPinXPos - 1;
				}
			}
			if (fxSliderPinXPos < 34 + fxSlider.getWidth() // fix
					- fxSliderPin.getWidth()) {
				if (input.isKeyDown(Input.KEY_RIGHT)) {
					fxSliderPinXPos = fxSliderPinXPos + 1;
				}
			}
			SoundEffects.setFxVolume(fxVolume);
		} else if (hoverValue == 1) {
			if (input.isKeyDown(Input.KEY_LEFT)) {
				if (musicSliderPinXPos > 45) {
					musicSliderPinXPos = musicSliderPinXPos - 1;
				}
			}
			if (musicSliderPinXPos < 456 + musicSlider.getWidth()
					- musicSliderPin.getWidth()) {
				if (input.isKeyDown(Input.KEY_RIGHT)) {
					musicSliderPinXPos = musicSliderPinXPos + 1;
				}
			}
		} else if (hoverValue == 2) {

			if (input.isKeyPressed(Input.KEY_RIGHT)) {
				cannonValue = (cannonValue + 1) % 5;
				fx.play(1, SoundEffects.getFxVolume());
			} else if (input.isKeyPressed(Input.KEY_LEFT)) {
				cannonValue--;
				if (cannonValue < 0) {
					cannonValue = 4;
				}
				fx.play(1, SoundEffects.getFxVolume());
			}
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				rightKeyIsDown = true;
			} else {
				rightKeyIsDown = false;
			}

			if (input.isKeyDown(Input.KEY_LEFT)) {
				leftKeyIsDown = true;
			} else {
				leftKeyIsDown = false;
			}

		} else if (hoverValue == 3) {
			if (input.isKeyPressed(Input.KEY_ENTER)) { // Change player's name,
				// different controls
				// perhaps
				playerName = nameField.getText();
				System.out.println(playerName);
				FileReader p;
				try {
					p = new FileReader("highscore/playername.dat");
					List<String> ps = new ArrayList<String>();
					ps.add(playerName);
					p.writePName(ps);

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else if (hoverValue == 4) {
			if (input.isKeyPressed(Input.KEY_ENTER)
					|| input.isKeyPressed(Input.KEY_SPACE)) {
				ThemeHandler.setCannon(cannonValue);
				sbg.enterState(States.MAINMENUVIEW.getID());
				hoverValue = 0;
			}
		}

		if (hoverValue == 3) {
			nameField.inputStarted();
		} else {
			nameField.inputEnded();
		}

		fxVolume = (float) (fxSliderPinXPos - 435)
				/ (fxSlider.getWidth() - fxSliderPin.getWidth());

		musicVolume = (float) (musicSliderPinXPos - 34 )
				/ (musicSlider.getWidth() - musicSliderPin.getWidth());

		input.clearKeyPressedRecord();

	}

	@Override
	public void moveMenuFocus() {
		for (Choices c : Choices.values()) {
			if (c.id() == hoverValue) {
				hoverYPos = c.yPos();
			}
		}
	}

	/**
	 * A method that returns a number from 0 to 5
	 * 
	 * @return which cannon is chosen
	 */
	public int getCannon() {
		return cannonValue;
	}

	/**
	 * A getter for the fx volume
	 * 
	 * @return a value between 0 and 1
	 */
	public float getFxValue() {
		return fxVolume;
	}

	/**
	 * A getter for the music volume
	 * 
	 * @return a value between 0 and 1
	 */
	public double getMusicVolume() {
		return musicVolume;
	}

	@Override
	public int getID() {
		return stateID;
	}

}
