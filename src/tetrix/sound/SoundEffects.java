package tetrix.sound;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundEffects {
	public static Sound sfx;

	public SoundEffects() throws SlickException{
		sfx = new Sound("Sound/test.wav");
		play();
	}
	public void play(){
		System.out.println("Ljud OK");
		sfx.play();
		
	}
	private void stop(){
		sfx.stop();
	}
	private void loop(){
		sfx.loop();
	}
	public static void main(String[]args) throws SlickException{
		SoundEffects se = new SoundEffects();
	}
}