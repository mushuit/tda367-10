package tetrix.sound;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

/**
 * A class that provides game music
 * @author Andreas Karlberg
 *
 */

public class GameMusic {
	private static Music gameMusic;

	public GameMusic() throws SlickException{
		gameMusic = new Music("Sound/background-music.wav");	// The game music
	}
	public void gameMusicPlay(){
		gameMusic.play();
	}
	public void gameMusicPause(){
		gameMusic.pause();
	}
	
	public void gameMusicLoop() {
		gameMusic.loop();
	}
	
	public void gameMusicStop(){
		gameMusic.stop();
	}

	public void getGameMusicVolume(){
		gameMusic.getVolume();
	}

	public static void setGameMusicVolume(float value){
		gameMusic.setVolume(value);
	}
}
