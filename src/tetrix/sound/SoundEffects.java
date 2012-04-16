package tetrix.sound;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundEffects {
	private Sound sfx;

	public SoundEffects() throws SlickException{
		sfx = new Sound("Sound/test.waw");
	}
	private void play(){
		sfx.play();
		}
	private void stop(){
		sfx.stop();
	}
	private void loop(){
		sfx.loop();
	}
}
