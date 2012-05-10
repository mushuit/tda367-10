package tetrix.view;
//TODO
//NYA BILDER

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tetrix.util.Util;
import tetrix.util.theme.ThemeHandler;
import tetrix.view.StateHandler.States;

/**
 * Class responsible for viewing different settings for the user to control.
 * @author Linus Karlsson & Jonathan Kara
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
	
	private int themeYPos;
	
	private Sound fx;
	private int hoverValue;
	private int hoverYPos;
	private int menuXPos;
	
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
		
		fxSlider = ThemeHandler.get(ThemeHandler.SLIDER_IMG);
		fxSliderXPos = 250;
		fxSliderYPos = effectsYPos;
		
		fxSliderPin = ThemeHandler.get(ThemeHandler.SLIDE_PIN_IMG);
		fxSliderPinHover = ThemeHandler.get(ThemeHandler.SLIDE_PIN_HOVER_IMG);
		fxSliderPinXPos = fxSliderXPos;
		fxSliderPinYPos = effectsYPos;
		
		musicSlider = ThemeHandler.get(ThemeHandler.SLIDER_IMG);
		musicSliderXPos = 250;
		musicSliderYPos = musicYPos;
		
		musicSliderPin = ThemeHandler.get(ThemeHandler.SLIDE_PIN_IMG);
		musicSliderPinHover = ThemeHandler.get(ThemeHandler.SLIDE_PIN_HOVER_IMG);
		musicSliderPinXPos = musicSliderXPos;
		musicSliderPinYPos = musicYPos;
		
		themeYPos = 390;
		
		fx = new Sound("sound/button.wav");
		hoverValue = 0;
		hoverYPos = effectsYPos;
		menuHover = ThemeHandler.get(ThemeHandler.HOVER_IMG);
		menuXPos = /*(Util.WINDOW_WIDTH/2) - (effects.getWidth()/2)*/ backXPos;					//Change
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
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		Input input = gc.getInput();
//		if (input.isKeyPressed(Input.KEY_T)){
//			ThemeHandler.setOverworldTheme();
//		}
		if (input.isKeyPressed(Input.KEY_DOWN)){
			hoverValue = (hoverValue + 1) % 4;
			fx.play();
		}
		else if(input.isKeyPressed(Input.KEY_UP)) {
			hoverValue--;
			if(hoverValue < 0) {
				hoverValue = 3;
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
			if(input.isKeyPressed(Input.KEY_RIGHT)) {			//Change theme
				
			}
			if(input.isKeyPressed(Input.KEY_LEFT)) {
				
			}
		}
		else if(hoverValue == 3) {
			if(input.isKeyPressed(Input.KEY_ENTER)) {
				sbg.enterState(States.MAINMENUVIEW.getID());
			}
		}
		
		 
		fxVolume = Double.parseDouble(Integer.toString(fxSliderPinXPos - fxSliderXPos))/Double.parseDouble(Integer.toString(fxSlider.getWidth() - fxSliderPin.getWidth()));
		musicVolume =Double.parseDouble(Integer.toString(musicSliderPinXPos - musicSliderXPos))/Double.parseDouble(Integer.toString(musicSlider.getWidth() - musicSliderPin.getWidth()));
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
			hoverYPos = themeYPos;      //THEME, still needs to be fixed
			break;
		case 3:
			hoverYPos = backYPos;
			break;
		}
	}
	//these getters return a value between 0 and 1
	public double getFxVolume(){
		return fxVolume;
	}
	
	public double getMusicVolume(){
		return musicVolume;
	}

	@Override
	public int getID() {
		return stateID;
	}

}
