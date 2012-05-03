package tetrix.view;
//TODO
//intervall på slider. Button down.

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tetrix.view.StateHandler.States;

/**
 * Class responsible for viewing different settings for the user to control.
 * @author Linus Karlsson
 *
 */
public class SettingsView extends BasicGameState {

	private int stateID;
	private Image background;
	private Image back;
	private Image backMouseOver;
	private Image sound;
	private Image effects;
	private Image music;
	
	private Image fxSlider;
	private Image fxSliderPin;
	private Image musicSlider;
	private Image musicSliderPin;
	
	private Image fxSliderPinHover;
	private Image musicSliderPinHover;

	private int backXpos;
	private int backYpos;
	private int soundXpos;
	private int soundYpos;
	private int effectsXpos;
	private int effectsYpos;
	private int musicXpos;
	private int musicYpos;
	
	private int fxSliderXpos;
	private int fxSliderYpos;
	private int fxSliderPinXpos;
	private int fxSliderPinYpos;
	
	private int musicSliderXpos;
	private int musicSliderYpos;
	private int musicSliderPinXpos;
	private int musicSliderPinYpos;
	
	
	private boolean inBackArea = false;
	private boolean inFxSliderArea = false;
	private boolean inFxPinArea = false;
	private boolean inMusicSliderArea = false;
	private boolean inMusicPinArea = false;
	
	private int fxVolume;
	private int musicVolume;
	
	
	public SettingsView(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		// TODO Auto-generated method stub
		background = new Image("img/settingsbackg.png");
		back = new Image("img/backButton.png");
		backMouseOver = new Image("img/backButtonMouseOver.png");
		backXpos = 200-(back.getWidth()/2);
		backYpos = 475;												//längst ner
		
		sound = new Image("img/sound.png");
		soundXpos = 200-(sound.getWidth()/2);	//var högersidan ska sitta
		soundYpos = 300;											//uppe
		
		effects = new Image("img/effects.png");
		effectsXpos = 240-(effects.getWidth());
		effectsYpos = 350;											//Mitten
		
		music = new Image("img/music.png");
		musicXpos = 240-(music.getWidth());		//var högersidan ska sitta
		musicYpos = 390;											//nere
		
		fxSlider = new Image("img/slider.png");
		fxSliderXpos = 250;
		fxSliderYpos = effectsYpos;
		
		fxSliderPin = new Image("img/slidePin.png");
		fxSliderPinHover = new Image ("img/slidePinMouseOver.png");
		fxSliderPinXpos = fxSliderXpos;
		fxSliderPinYpos = effectsYpos;
		
		musicSlider = new Image("img/slider.png");
		musicSliderXpos = 250;
		musicSliderYpos = musicYpos;
		
		musicSliderPin = new Image("img/slidePin.png");
		musicSliderPinHover = new Image ("img/slidePinMouseOver.png");
		musicSliderPinXpos = musicSliderXpos;
		musicSliderPinYpos = musicYpos;
		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		background.draw(0,0);
		sound.draw(soundXpos, soundYpos);
		effects.draw(effectsXpos, effectsYpos);
		music.draw(musicXpos, musicYpos);
		
		fxSlider.draw(fxSliderXpos, fxSliderYpos);
		musicSlider.draw(musicSliderXpos, musicSliderYpos);
		
		back.draw(backXpos, backYpos);
		if(inBackArea){
			backMouseOver.draw(backXpos, backYpos);
		} else {
			back.draw(backXpos, backYpos);
		}
		if(inFxPinArea){
			
			fxSliderPinHover.draw(fxSliderPinXpos, fxSliderPinYpos);
		} else {
			fxSliderPin.draw(fxSliderPinXpos, fxSliderPinYpos);
		}
		if(inMusicPinArea){
			musicSliderPinHover.draw(musicSliderPinXpos, musicSliderPinYpos);
		} else {
			musicSliderPin.draw(musicSliderPinXpos, musicSliderPinYpos);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		Input input = gc.getInput();
		 
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		
		if( ( mouseX >= backXpos && mouseX <= backXpos+back.getWidth()) &&
			( mouseY >= backYpos && mouseY <= backYpos+back.getHeight()) ){
			inBackArea = true;
		} else{
			inBackArea = false;
		}
		if ( (mouseX >= fxSliderPinXpos && mouseX <= fxSliderPinXpos + fxSliderPin.getWidth()) &&
			    ( mouseY >= fxSliderPinYpos && mouseY <= fxSliderPinYpos + fxSliderPin.getHeight())){
			inFxPinArea = true;
		} else {
			inFxPinArea = false;
		}
		if ( (mouseX >= musicSliderPinXpos && mouseX <= musicSliderPinXpos + musicSliderPin.getWidth()) &&
			    ( mouseY >= musicSliderPinYpos && mouseY <= musicSliderPinYpos + musicSliderPin.getHeight())){
			inMusicPinArea = true;
		} else {
			inMusicPinArea = false;
		}
		if ( (mouseX >= fxSliderXpos + (fxSliderPin.getWidth()/2) && mouseX <= (fxSliderXpos + fxSlider.getWidth() - musicSliderPin.getWidth()/2)) &&
			    ( mouseY >= fxSliderYpos && mouseY <= fxSliderYpos + fxSlider.getHeight())){
			inFxSliderArea = true;
		} else {
			inFxSliderArea = false;
		}
		if ( (mouseX >= musicSliderXpos + (musicSliderPin.getWidth()/2) && mouseX <= (musicSliderXpos + musicSlider.getWidth() - musicSliderPin.getWidth()/2) &&
			    ( mouseY >= musicSliderYpos && mouseY <= musicSliderYpos + musicSlider.getHeight()))){
			inMusicSliderArea = true;
		} else {
			inMusicSliderArea = false;
		}
		
		if(inBackArea){
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) ){
				sbg.enterState(States.MAINMENUVIEW.getID());
			}
		} else if(inFxSliderArea){
			//if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) ){
				if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)/* && (mouseX >= fxSliderXpos) && (mouseX <= fxSliderXpos+fxSlider.getWidth())*/){
					fxSliderPinXpos = mouseX -(fxSliderPin.getWidth()/2);
				}
			//}
		} else if(inMusicSliderArea){
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) /*&& (mouseX >= musicSliderXpos) && (mouseX <= musicSliderXpos+musicSlider.getWidth())*/){
				musicSliderPinXpos = mouseX -(musicSliderPin.getWidth()/2);
			}
		}
		
		fxVolume = fxSliderPinXpos - fxSliderXpos;
		musicVolume = musicSliderPinXpos - musicSliderXpos;
		System.out.println(""+ fxVolume);
		System.out.println(""+ musicVolume);
	}

	@Override
	public int getID() {
		return stateID;
	}

}
