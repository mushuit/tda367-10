package tetrix.sound;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class GameMusic {
	public static Music music;

	public GameMusic() throws SlickException{
		music = new Music("Sound/test.wav");
	}
	private void play(){
		music.play();
	}
	private void stop(){
		music.stop();
	}
	private void loop() {
		music.loop();
	}
	public static void main(String[]args){
		music.play();
		if (true) {
			music.loop();
		}
	}
}