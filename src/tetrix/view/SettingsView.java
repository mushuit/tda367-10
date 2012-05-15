package tetrix.view;
//TODO
//NYA BILDER
//Mellanslag i menyer
//Hover värde 0 varje gång - done
//övergångarna
//att de andra alternativen inte ska lyssna på piltangenternas inmatning

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
import tetrix.util.Util;
import tetrix.view.StateHandler.States;
import tetrix.view.theme.ThemeHandler;

/**
 * Class responsible for viewing different settings for the user to control.
 * @author Jonathan Kara & Andreas Karlberg
 *
 */
public class SettingsView extends BasicGameState {

	private int stateID;

	private Image tetrixLogo;
	private Image background;
	private Image back;
	private Image sound;
	private Image effects;
	private Image music;

	private Image fxSlider;
	private Image fxSliderPin;
	private Image musicSlider;
	private Image musicSliderPin;

	private Image fxSliderPinHover;
	private Image musicSliderPinHover;

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
	
	private int backXPos;
	private int backYPos;
	private int soundXPos;
	private int soundYPos;
	private int effectsXPos;
	private int effectsYPos;
	private int musicXPos;
	private int musicYPos;

	private int fxSliderXPos;
	private int fxSliderYPos;
	private int fxSliderPinXPos;
	private int fxSliderPinYPos;

	private int musicSliderXPos;
	private int musicSliderYPos;
	private int musicSliderPinXPos;
	private int musicSliderPinYPos;

	private double fxVolume;						
	private double musicVolume;

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
	private TrueTypeFont inputFont;
	private TrueTypeFont inputDescFont;
	private String playerName;
	private static final int windowHeight = 600;
	private static final int windowWidth = 400;

	private int dialogWidth = 210;
	private int dialogHeight = 100;
	
	private boolean rightKeyIsDown;
	private boolean leftKeyIsDown;

	public SettingsView(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		background = ThemeHandler.get(ThemeHandler.BACKGROUND_IMG);
		tetrixLogo = ThemeHandler.get(ThemeHandler.TETRIX_LOGO_IMG);
		back = ThemeHandler.get(ThemeHandler.BACK_IMG);
		backXPos = 200-(back.getWidth()/2);
		backYPos = 460;												//längst ner

		sound = ThemeHandler.get(ThemeHandler.SOUND_IMG);
		soundXPos = 200-(sound.getWidth()/2);	//var högersidan ska sitta
		soundYPos = 200;											//uppe

		effects = ThemeHandler.get(ThemeHandler.EFFECTS_IMG);
		effectsXPos = 240-(effects.getWidth());
		effectsYPos = 250;											//Mitten

		music = ThemeHandler.get(ThemeHandler.MUSIC_IMG);
		musicXPos = 240-(music.getWidth());		//var högersidan ska sitta
		musicYPos = 320;											//nere

		sound = ThemeHandler.get(ThemeHandler.SOUND_IMG);
		soundXPos = 200-(sound.getWidth()/2);	//var högersidan ska sitta
		soundYPos = 200;																		//Change

		effects = ThemeHandler.get(ThemeHandler.EFFECTS_IMG);
		effectsXPos = 240-(effects.getWidth());
		effectsYPos = 250;																		//Change

		music = ThemeHandler.get(ThemeHandler.MUSIC_IMG);
		musicXPos = 240-(music.getWidth());		//var högersidan ska sitta
		musicYPos = 320;											 							//Change

		fxSlider = ThemeHandler.get(ThemeHandler.SLIDER_IMG);
		fxSliderXPos = 250;
		fxSliderYPos = effectsYPos;

		fxSliderPin = ThemeHandler.get(ThemeHandler.SLIDE_PIN_IMG);
		fxSliderPinHover = ThemeHandler.get(ThemeHandler.SLIDE_PIN_HOVER_IMG);
		fxSliderPinXPos = fxSliderXPos + fxSlider.getWidth() - fxSliderPin.getWidth();
		fxSliderPinYPos = effectsYPos-3;

		musicSlider = ThemeHandler.get(ThemeHandler.SLIDER_IMG);
		musicSliderXPos = 250;
		musicSliderYPos = musicYPos;

		musicSliderPin = ThemeHandler.get(ThemeHandler.SLIDE_PIN_IMG);
		musicSliderPinHover = ThemeHandler.get(ThemeHandler.SLIDE_PIN_HOVER_IMG);
		musicSliderPinXPos = musicSliderXPos + musicSlider.getWidth() - musicSliderPin.getWidth();
		musicSliderPinYPos = musicYPos-3;

		cannonXPos = musicSliderXPos;															//temporary value
		cannonYPos = 390;																		//change

		cannon = ThemeHandler.getBlockOrCannon(ThemeHandler.CANNON_IMG);
		cannon2 = ThemeHandler.getBlockOrCannon(ThemeHandler.CANNON2_IMG);
		cannon3 = ThemeHandler.getBlockOrCannon(ThemeHandler.CANNON3_IMG);
		cannon4 = ThemeHandler.getBlockOrCannon(ThemeHandler.CANNON4_IMG);
		cannon5 = ThemeHandler.getBlockOrCannon(ThemeHandler.CANNON5_IMG);

		cannonValue = 0;
		
		rightArrow = ThemeHandler.get(ThemeHandler.RIGHT_ARROW_IMG);
		rightArrowHover = ThemeHandler.get(ThemeHandler.RIGHT_ARROW_HOVER_IMG);
		leftArrow = ThemeHandler.get(ThemeHandler.LEFT_ARROW_IMG);
		leftArrowHover = ThemeHandler.get(ThemeHandler.LEFT_ARROW_HOVER_IMG);
		
		rightArrowXpos = cannonXPos + cannon.getWidth() + 5;
		leftArrowXpos = cannonXPos - leftArrow.getWidth() - 5;

		fx = new Sound("sound/button.wav");
		hoverValue = 0;
		hoverYPos = effectsYPos;
		menuHover = ThemeHandler.get(ThemeHandler.HOVER_IMG);
		menuXPos = /*(Util.WINDOW_WIDTH/2) - (effects.getWidth()/2)*/ backXPos;					//Change
		playerYPos = 420;
		menuXPos = (Util.WINDOW_WIDTH/2) - (menuHover.getWidth()/2);							//Change hoverpic
		playerYPos = 420;																		//change

		back = ThemeHandler.get(ThemeHandler.BACK_IMG);
		backXPos = 200-(back.getWidth()/2);
		backYPos = 460;																			//Change

		Font font = new Font("Verdana", Font.BOLD, 20);
		Font descriptionFont = new Font("Verdana", Font.BOLD, 12);
		inputFont = new TrueTypeFont(font, true);
		inputDescFont = new TrueTypeFont(descriptionFont, true);

		int textFieldWidth = 200;
		int textFieldHeight = 30;
		nameField = new TextField(gc, inputFont, windowWidth / 2
				- textFieldWidth / 2, (playerYPos + textFieldHeight /2),
				textFieldWidth, textFieldHeight);
		nameField.setBackgroundColor(Color.white);
		nameField.setBorderColor(Color.pink);
		nameField.setTextColor(Color.black);
		
		rightKeyIsDown = false;
		leftKeyIsDown = false;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics arg2)
			throws SlickException {
		background.draw(0,0);
		menuHover.draw(menuXPos, hoverYPos);
		tetrixLogo.draw(Util.WINDOW_WIDTH/2-(tetrixLogo.getWidth()/2), 100);
		sound.draw(soundXPos, soundYPos);
		effects.draw(effectsXPos, effectsYPos);
		music.draw(musicXPos, musicYPos);

		fxSlider.draw(fxSliderXPos, fxSliderYPos);
		musicSlider.draw(musicSliderXPos, musicSliderYPos);
		fxSliderPin.draw(fxSliderPinXPos, fxSliderPinYPos);
		musicSliderPin.draw(musicSliderPinXPos, musicSliderPinYPos);

		if(cannonValue == 0) {
			cannon.draw(cannonXPos, cannonYPos);
		} else if(cannonValue == 1) {
			cannon2.draw(cannonXPos, cannonYPos);
		} else if(cannonValue == 2) {
			cannon3.draw(cannonXPos, cannonYPos);
		} else if(cannonValue == 3) {
			cannon4.draw(cannonXPos, cannonYPos);
		} else if(cannonValue == 4) {
			cannon5.draw(cannonXPos, cannonYPos);
		}
		back.draw(backXPos, backYPos);
		if(hoverValue == 0) {
			fxSliderPinHover.draw(fxSliderPinXPos, fxSliderPinYPos);
		} else {
			fxSliderPin.draw(fxSliderPinXPos, fxSliderPinYPos);
		}
		if(hoverValue == 1) {
			musicSliderPinHover.draw(musicSliderPinXPos, musicSliderPinYPos);
		} else{
			musicSliderPin.draw(musicSliderPinXPos, musicSliderPinYPos);
		}
		
		if (rightKeyIsDown){
			rightArrowHover.draw(rightArrowXpos, cannonYPos);
		} else if (!rightKeyIsDown){
			rightArrow.draw(rightArrowXpos, cannonYPos);
		}
		if (leftKeyIsDown){
			leftArrowHover.draw(leftArrowXpos, cannonYPos);
		} else if (!leftKeyIsDown){
			leftArrow.draw(leftArrowXpos, cannonYPos);
		}

		arg2.setColor(Color.lightGray);
		inputDescFont.drawString((windowWidth/2 - dialogWidth/2) + 50, (playerYPos), "Enter your name", Color.green);
		nameField.render(gc, arg2);
		nameField.setFocus(true);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		Input input = gc.getInput();

		if (input.isKeyPressed(Input.KEY_DOWN)){
			hoverValue = (hoverValue + 1) % 5;
			fx.play();
		}
		else if(input.isKeyPressed(Input.KEY_UP)) {
			hoverValue--;
			if(hoverValue < 0) {
				hoverValue = 4;
			}
			fx.play();
		}
		
		moveMenuFocus();

		if(hoverValue == 0) {
			if(input.isKeyDown(Input.KEY_LEFT)) {
				if(fxSliderPinXPos > fxSliderXPos){
					fxSliderPinXPos = fxSliderPinXPos - 1;
				}
			}
			if(fxSliderPinXPos < fxSliderXPos + fxSlider.getWidth() - fxSliderPin.getWidth()){
				if(input.isKeyDown(Input.KEY_RIGHT)) {
					fxSliderPinXPos = fxSliderPinXPos + 1;
				}
			}
		}
		else if(hoverValue == 1) {
			if(input.isKeyDown(Input.KEY_LEFT)) {
				if(musicSliderPinXPos > musicSliderXPos){
					musicSliderPinXPos = musicSliderPinXPos - 1;
				}
			}
			if(musicSliderPinXPos < musicSliderXPos + musicSlider.getWidth() - musicSliderPin.getWidth()){
				if(input.isKeyDown(Input.KEY_RIGHT)) {
					musicSliderPinXPos = musicSliderPinXPos + 1;
				}
			}
		}
		else if(hoverValue == 2) {
			
			if (input.isKeyPressed(Input.KEY_RIGHT)){
				cannonValue = (cannonValue + 1) % 5;
				fx.play();
			}
			else if(input.isKeyPressed(Input.KEY_LEFT)) {
				cannonValue--;
				if(cannonValue < 0) {
					cannonValue = 4;
				}
				fx.play();
			}
			if (input.isKeyDown(Input.KEY_RIGHT)){
				rightKeyIsDown = true;
			} else {
				rightKeyIsDown = false;
			}
			
			if(input.isKeyDown(Input.KEY_LEFT)) {
				leftKeyIsDown = true;
			} else {
				leftKeyIsDown = false;
			}
		
		} else if(hoverValue == 3) {
				if(input.isKeyPressed(Input.KEY_ENTER)) {		//Change player's name, different controls perhaps
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

		} else if(hoverValue == 4) {
			if(input.isKeyPressed(Input.KEY_ENTER)) {
				ThemeHandler.setCannon(cannonValue);
				sbg.enterState(States.MAINMENUVIEW.getID());
				hoverValue = 0;
			}
		}
		 
		fxVolume = Double.parseDouble(Integer.toString(fxSliderPinXPos - fxSliderXPos))/
				Double.parseDouble(Integer.toString(fxSlider.getWidth() - fxSliderPin.getWidth()));		

		musicVolume =Double.parseDouble(Integer.toString(musicSliderPinXPos - musicSliderXPos))/
				Double.parseDouble(Integer.toString(musicSlider.getWidth() - musicSliderPin.getWidth()));
		
	}

	public void moveMenuFocus() {
		switch(hoverValue) {
		case 0:
			hoverYPos = effectsYPos;
			break;
		case 1:
			hoverYPos = musicYPos;
			break;
		case 2:
			hoverYPos = cannonYPos;
			break;
		case 3:
			hoverYPos = playerYPos;
			break;
		case 4:
			hoverYPos = backYPos;
			break;
		}
	}
	/**
	 * A method that returns a number from 0 to 5
	 * @return which cannon is chosen
	 */
	public int getCannon(){
		return cannonValue;
	}
	/**
	 * A getter for the fx volume
	 * @return a value between 0 and 1
	 */
	public double getFxVolume(){
		return fxVolume;
	}

	/**
	 * A getter for the music volume
	 * @return a value between 0 and 1
	 */
	public double getMusicVolume(){
		return musicVolume;
	}

	@Override
	public int getID() {
		return stateID;
	}

}
