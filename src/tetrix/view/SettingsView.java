package tetrix.view;

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
import tetrix.sound.GameMusic;
import tetrix.sound.SoundEffects;
import tetrix.util.Util;
import tetrix.view.StateHandler.States;
import tetrix.view.theme.ThemeHandler;

/**
 * Class responsible for viewing different settings for the user to control.
 * 
 * @author Jonathan Kara, Andreas Karlberg, Linus Karlsson
 * 
 */
public class SettingsView extends BasicGameState implements IMultipleChoices{

	private int stateID;

	private Image tetrixLogo;
	private Image background;
	private Image back;
	private Image effects;
	private Image music;

	private Image fxSliderPin;
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
	private int hoverValue;
	private int hoverYPos;
	private int menuXPos;
	private int playerYPos;

	
	@SuppressWarnings("deprecation")
	private TrueTypeFont inputFont;
	@SuppressWarnings("deprecation")
	private TrueTypeFont inputDescFont;

	private int dialogWidth = 210;
	private TextField nameField;
	private boolean rightKeyIsDown;
	private boolean leftKeyIsDown;
	private boolean isAcceptingInput;
	
	private enum Choices {
		FXVOLUME(0, 200), MUSICVOLUME(1, 270), CANNONCHANGER(2, 340), PLAYERNAME(3, 410), BACK(4, 480);

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
		effects = ThemeHandler.get(ThemeHandler.FX_VOLUME_IMG);
		music = ThemeHandler.get(ThemeHandler.MUSIC_VOLUME_IMG);
		
		cannon = ThemeHandler.getBlockOrCannon(ThemeHandler.CANNON_IMG);
		cannon2 = ThemeHandler.getBlockOrCannon(ThemeHandler.CANNON2_IMG);
		cannon3 = ThemeHandler.getBlockOrCannon(ThemeHandler.CANNON3_IMG);
		cannon4 = ThemeHandler.getBlockOrCannon(ThemeHandler.CANNON4_IMG);
		cannon5 = ThemeHandler.getBlockOrCannon(ThemeHandler.CANNON5_IMG);
		
		musicSliderPin = ThemeHandler.get(ThemeHandler.SLIDE_PIN_IMG);
		fxSliderPin = ThemeHandler.get(ThemeHandler.SLIDE_PIN_IMG);
		rightArrow = ThemeHandler.get(ThemeHandler.RIGHT_ARROW_IMG);
		rightArrowHover = ThemeHandler.get(ThemeHandler.RIGHT_ARROW_HOVER_IMG);
		leftArrow = ThemeHandler.get(ThemeHandler.LEFT_ARROW_IMG);
		leftArrowHover = ThemeHandler.get(ThemeHandler.LEFT_ARROW_HOVER_IMG);
		back = ThemeHandler.get(ThemeHandler.BACK_IMG);
		
		fxSliderPinXPos = menuXPos+effects.getWidth();
		musicSliderPinXPos = menuXPos + music.getWidth();
		cannonXPos = (Util.WINDOW_WIDTH / 2) - (cannon.getWidth() / 2);
		cannonYPos = Choices.CANNONCHANGER.yPos();
		cannonValue = 0;

		rightArrowXpos = cannonXPos + cannon.getWidth() + 5;
		leftArrowXpos = cannonXPos - leftArrow.getWidth() - 5;
		menuXPos = (Util.WINDOW_WIDTH / 2) - (menuHover.getWidth() / 2);
		playerYPos = Choices.PLAYERNAME.yPos;

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
		isAcceptingInput = false;

		try {
			nameField.setText(FileReader.getPlayerName());
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

		fxSliderPin.draw(fxSliderPinXPos, Choices.FXVOLUME.yPos()+6);
		musicSliderPin.draw(musicSliderPinXPos, Choices.MUSICVOLUME.yPos()+6);

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
		nameField.setFocus(isAcceptingInput);

		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		Input input = gc.getInput();

		if (input.isKeyPressed(Input.KEY_DOWN)) {
			hoverValue = (hoverValue + 1) % Choices.values().length;
			SoundEffects.instance().menuClickPlay();
		} else if (input.isKeyPressed(Input.KEY_UP)) {
			hoverValue--;
			if (hoverValue < 0) {
				hoverValue = Choices.values().length - 1;
			}
			SoundEffects.instance().menuClickPlay();
		}

		moveMenuFocus();

		if (hoverValue == Choices.FXVOLUME.id()) {
			if (input.isKeyDown(Input.KEY_LEFT)) {
				if (fxSliderPinXPos > effects.getWidth()/2+menuXPos) {
					fxSliderPinXPos = fxSliderPinXPos - 1;
				}
			}
			if (fxSliderPinXPos < menuXPos + (effects.getWidth())
					- (fxSliderPin.getWidth()*1.5)+2) {
				if (input.isKeyDown(Input.KEY_RIGHT)) {
					fxSliderPinXPos = fxSliderPinXPos + 1;
				}
			}
			SoundEffects.setFxVolume(fxVolume);
		} else if (hoverValue == Choices.MUSICVOLUME.id()) {
			if (input.isKeyDown(Input.KEY_LEFT)) {
				if (musicSliderPinXPos > music.getWidth()/2+menuXPos) {
					musicSliderPinXPos = musicSliderPinXPos - 1;
				}
			}
			if (musicSliderPinXPos < menuXPos + (music.getWidth())
					- (musicSliderPin.getWidth()*1.5)+2) {
				if (input.isKeyDown(Input.KEY_RIGHT)) {
					musicSliderPinXPos = musicSliderPinXPos + 1;
				}
			}
			GameMusic.setGameMusicVolume(musicVolume);
		} else if (hoverValue == Choices.CANNONCHANGER.id()) {
			cannonChanger(input);
			
		} else if (hoverValue == Choices.BACK.id()) {
			if (input.isKeyPressed(Input.KEY_ENTER)) {
				FileReader p;
				try {
					p = new FileReader("highscore/playername.dat");
					p.writePlayerName(nameField.getText());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ThemeHandler.setCannon(cannonValue);
				sbg.enterState(States.MAINMENUVIEW.getID());
			}
		}

		if(hoverValue == Choices.PLAYERNAME.id()) {
			isAcceptingInput = true;
		} else {
			isAcceptingInput = false;
		}
		
		fxVolume = (float) (fxSliderPinXPos - (effects.getWidth()/2) - menuXPos)
				/ (menuXPos + effects.getWidth() - fxSliderPin.getWidth());

		musicVolume = (float) (musicSliderPinXPos - (music.getWidth()/2) - menuXPos)
				/ (music.getWidth()/2 - musicSliderPin.getWidth());

		try {
			GameMusic.instance().setGameMusicVolume(musicVolume);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		input.clearKeyPressedRecord();
		System.out.println(musicVolume + "    " +fxVolume);

		System.out.println("menuXPos: " + menuXPos);
		System.out.println("effects.getWidth(): " + effects.getWidth());
		System.out.println("fxSliderPinXPos: " + fxSliderPinXPos);
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
	 * A getter for the music's volume
	 * 
	 * @return a value between 0 and 1
	 */
	public double getMusicVolume() {
		return musicVolume;
	}
	
	public void cannonChanger(Input input) throws SlickException{
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			cannonValue = (cannonValue + 1) % 5;
			SoundEffects.instance().menuClickPlay();
		} else if (input.isKeyPressed(Input.KEY_LEFT)) {
			cannonValue--;
			if (cannonValue < 0) {
				cannonValue = 4;
			}
			SoundEffects.instance().menuClickPlay();
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
	}
	
//	public void checkInput(Input input, StateBasedGame sbg){
//		
//	}

	@Override
	public int getID() {
		return stateID;
	}

}
