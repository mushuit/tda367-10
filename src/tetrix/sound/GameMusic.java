package tetrix.sound;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class GameMusic {
	private Music music;

	public GameMusic() throws SlickException{
		music = new Music("Sound/test.wav");
	}
	private void play(){
		music.play();
	}
	private void loop() {
		music.loop();
	}
	
}
